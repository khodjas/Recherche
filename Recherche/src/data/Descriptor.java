package data;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import core.SearchSite;
import log.LoggerUtility;

/**
 * 
 * Classe repr�sentant le couple de donn�es Word et la liste des Sites
 *
 */
public class Descriptor implements Serializable {

	private ArrayList<Site> sites;
	// l'�lt keyword est la partie m�re du descripteur
	private Word keyword;
	private static Logger logger = LoggerUtility.getLogger(SearchSite.class);

	/**
	 * Constructeur
	 * 
	 * @param keyword
	 *            Le mot-cl�
	 */
	public Descriptor(Word keyword) {
		sites = new ArrayList<Site>();
		this.keyword = keyword;

	}

	/**
	 * Ajoute un site
	 * 
	 * @param site
	 *            le site � ajouter
	 */
	public void addSite(Site site) {
		// on v�rifie que le site n'est pas d�j� �xistant
		if (!sites.contains(site)) {
			sites.add(site);
		}
	}

	/**
	 * 
	 * @return le mot-cl�
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
	 * @return vrai si le descripteur � le m�me mot que descriptor ,faux sinon
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
