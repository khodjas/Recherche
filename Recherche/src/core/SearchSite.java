package core;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import data.Index;
import data.Site;
import log.LoggerUtility;

public class SearchSite {
	private final String fileName = "fichier.ser";
	private ArrayList<String> result;
	private Index index;
	private static Logger logger=LoggerUtility.getLogger(SearchSite.class);

	public SearchSite() {
		index = new Index();
		index.load(fileName);
		result = new ArrayList<String>();
	}

	public void setResult(ArrayList<String> sites) {
		result = sites;
	}

	public ArrayList<String> getResult() {
		return result;
	}

	public void search(String request) throws NullPointerException{
		if (!getResult().isEmpty()) {
			setResult(new ArrayList<String>());
		}
		request=request.replaceAll("( ) *", " ");
		String[] keywords = request.split(" ");
		for (int indexNum = 0; indexNum < keywords.length; indexNum++) {
			//try{
				ArrayList<Site> tmp = index.getSpecificDescriptor(keywords[indexNum]).getSites();
			
				for (Site site : tmp) {
					if(!getResult().contains(site.getUrl())){
					getResult().add(site.getUrl());
					}
				}
//				}catch(NullPointerException npe){
//					logger.warn("aucun site concernant le mot "+keywords[indexNum]);
//				}
			if(getResult().isEmpty()){
				throw new NullPointerException();
			}
			
		}
	}

	public static void main(String[] args) {
		SearchSite search = new SearchSite();
		search.search("google");
		for (String string : search.getResult()) {
			System.out.println(string);
		}
	}
}
