package data;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import core.SearchSite;
import log.LoggerUtility;

/**
 * 
 * Classe représentant le couple de données Word et la liste des Sites
 *
 */
public class Descriptor implements Serializable {

	private ArrayList<Site> sites;
	// l'élt keyword est la partie mère du descripteur
	private Word keyword;
	private static Logger logger = LoggerUtility.getLogger(SearchSite.class);

	/**
	 * Constructeur
	 * 
	 * @param keyword
	 *            Le mot-clé
	 */
	public Descriptor(Word keyword) {
		sites = new ArrayList<Site>();
		this.keyword = keyword;

	}

	/**
	 * Ajoute un site
	 * 
	 * @param site
	 *            le site à ajouter
	 */
	public void addSite(Site site) {
		// on vérifie que le site n'est pas déjà éxistant
		if (!sites.contains(site)) {
			sites.add(site);
		}
	}

	/**
	 * 
	 * @return le mot-clé
	 */
	public Word getKeyword() {
		return keyword;
	}

	/**
	 * 
	 * @return l'ensemble des sites
	 */
	public ArrayList<Site> getSites() {
		return sites;
	}

	

	/**
	 * 
	 * @param descriptor
	 * @return vrai si le descripteur à le même mot que descriptor ,faux sinon
	 */
	public boolean compare(Descriptor descriptor) {
		if (this.getKeyword().equals(descriptor.getKeyword())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 */
	public void afficheSites() {
		System.out.println(keyword);
		for (Site site : sites) {
			System.out.println(site.getUrl());
		}
	}
}
