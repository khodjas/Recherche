package core;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import data.Descriptor;
import data.Index;
import data.Site;
import log.LoggerUtility;

public class SearchSite {
	private final String fileName = "fichier.ser";
	private ArrayList<String> result;
	private Index index;
	private static Logger logger = LoggerUtility.getLogger(SearchSite.class);

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

	public void search(String request) throws NullPointerException {
		if (!getResult().isEmpty()) {
			setResult(new ArrayList<String>());
		}
		request = request.replaceAll("( ) *", " ");
		String[] keywords = request.split(" ");
		for (int indexNum = 0; indexNum < keywords.length; indexNum++) {
			Descriptor descriptor = index.getSpecificDescriptor(keywords[indexNum]);
			ArrayList<Site> tmp = descriptor.getSites();
			String string = descriptor.getKeyword().toString();
			if (string.contains("synonyms: ")) {
				String[] strings = string.split("synonyms: ");
				String[] synonyms = strings[1].split(" ");
				tmp.addAll(RecoveredSynonym.listRelatedSite(index, synonyms));
			}
			for (Site site : tmp) {
				if (!getResult().contains(site.getUrl())) {
					getResult().add(site.getUrl());
				}
			}
			if (getResult().isEmpty()) {
				logger.warn("aucun site concernant le mot " + keywords[indexNum]);
			}

		}
		if (getResult().isEmpty()) {	
			throw new NullPointerException();
		}
	}

}
