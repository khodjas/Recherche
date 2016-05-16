package core;

import java.util.ArrayList;

import data.Descriptor;
import data.Index;
import data.Site;
/**
 * 
 * Classe fonctionnelle contenant les méthodes pour la sémantique 
 *
 */
public class RecoveredSynonym {

	/**
	 * récupère la liste des sites des descripteur synonymes
	 * @param index
	 * @param synonyms
	 * @return liste des sites
	 */
	public static ArrayList<Site> listRelatedSite(Index index, String[] synonyms) {
		ArrayList<Site> tmp = new ArrayList<Site>();

		for (String synonym : synonyms) {
			try{
			tmp.addAll(index.getSynonymDescriptor(synonym).getSites());
			}catch(NullPointerException npe){
				return tmp;
			}
		}
		return tmp;
	}
/**
 * vérifie si le descripteur synonyme existe
 * @param descriptor1
 * @param descriptor2
 * @return vrai si c'est un descripteur synonymes ,faux sinon
 */
	public static boolean synonymExist(Descriptor descriptor1, Descriptor descriptor2) {
		if (descriptor1.getKeyword().getValue().equals(descriptor2.getKeyword().getValue())) {
			return true;
		}
		return false;
	}

}
