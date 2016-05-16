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
	private ArrayList<Site> resultSite;
	private Index index;
	private static Logger logger = LoggerUtility.getLogger(SearchSite.class);

	public SearchSite() {
		index = new Index();
		index.load(fileName);
		result = new ArrayList<String>();
		resultSite = new ArrayList<Site>();
	}

	public void setResult(ArrayList<String> sites) {
		result = sites;
	}

	public ArrayList<String> getResult() {
		return result;
	}

	public void setResultSite(ArrayList<Site> sites) {
		resultSite = sites;

	}

	public ArrayList<Site> getResultSite() {
		return resultSite;
	}

	/**
	 * permet de ranger les sites par ordre de récurrence
	 * 
	 * @return sitesTriee liste des sites trier
	 */
	public ArrayList<Site> getSitesTriee(ArrayList<Site> sitesTemp) {
		ArrayList<Site> siteTriee = new ArrayList<Site>();

		Site site2 = null;
		while (!sitesTemp.isEmpty()) {
			for (Site site : sitesTemp) {
				if (site2 == null) {
					site2 = site;
				} else {
					if (site2.getWord().getOccurence() <= site.getWord().getOccurence()) {
						site2 = site;
					}
				}
			}
			siteTriee.add(site2);
			logger.info("le mot entree est : " + site2.getWord() + " d'occurence : " + site2.getWord().getOccurence());
			sitesTemp.remove(site2);
			site2 = null;
		}
		return siteTriee;

	}

	public void search(String request) throws NullPointerException {
		if (!getResult().isEmpty()) {
			setResult(new ArrayList<String>());
			setResultSite(new ArrayList<Site>());
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
					getResultSite().add(site);
				}
			}
			if (getResult().isEmpty()) {
				logger.warn("aucun site concernant le mot " + keywords[indexNum]);
			}

		}

		setResultSite(getSitesTriee(getResultSite()));
		setResult(new ArrayList<String>());
		for (Site site : getResultSite()) {
			getResult().add(site.getUrl());
		}
		if (getResult().isEmpty()) {
			throw new NullPointerException();
		}
	}

}
