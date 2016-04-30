package core;

import java.util.ArrayList;

import data.Descriptor;
import data.Index;
import data.Site;

public class RecoveredSynonym {


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

	public static boolean synonymExist(Descriptor descriptor1, Descriptor descriptor2) {
		if (descriptor1.getKeyword().getValue().equals(descriptor2.getKeyword().getValue())) {
			return true;
		}
		return false;
	}

}
