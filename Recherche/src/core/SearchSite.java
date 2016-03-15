package core;

import java.util.ArrayList;

import data.Index;

public class SearchSite {
	private final String fileName = "";
	private ArrayList<String> result;
	private Index index;

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

	public void Search(String request) throws NullPointerException{
		if (!getResult().isEmpty()) {
			setResult(new ArrayList<String>());
		}
		String[] keywords = request.split(" ");
		for (int indexNum = 0; indexNum < keywords.length; indexNum++) {
				ArrayList<String> tmp = index.getSpecificDescriptor(keywords[indexNum]).getSites();
				for (String site : tmp) {
					getResult().add(site);
				}
			
			throw new NullPointerException();
		}
	}

	public static void main(String[] args) {
		SearchSite search = new SearchSite();
		search.Search("googl");
		for (String string : search.getResult()) {
			System.out.println(string);
		}
	}
}
