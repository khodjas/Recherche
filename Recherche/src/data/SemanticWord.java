package data;

import java.util.ArrayList;

/**
 * 
 * Extension de FamilyWord qui permettra d'avoir une recherche plus appronfondis
 * en ajoutant la sémantique
 *
 */
public class SemanticWord extends FamilyWord {
	private ArrayList<String> synonyms;

	public SemanticWord(String value) {
		super(value);
		synonyms = new ArrayList<String>();

	}

	/**
	 * 
	 * @return l'ensemble des synonymes
	 */
	public ArrayList<String> getSynonyms() {
		return synonyms;
	}

	/**
	 * amélioration du equals qui compare aussi si le mot-clef correspond aux
	 * synonymes de mot
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if ((getClass() != obj.getClass())) {
			if (getClass().getSuperclass() != obj.getClass()) {
				if (getClass().getSuperclass().getSuperclass() != obj.getClass()) {
					return false;
				}
			}
		}
		Word other = (Word) obj;
		if (getValue() == null) {
			if (other.getValue() != null) {
				return false;
			}
		} else if (!getValue().equals(other.getValue()) && !super.getFamily().contains(other.getValue())) {
			return false;
		}

		return true;
	}

	/**
	 * ajoute un synonyme au motclé
	 * @param synonym un synonyme du motclé
	 */
	public void addWordSynonym(String synonym) {
		getSynonyms().add(synonym);
	}

	/**
	 * ajoute une liste de mots synonymes au motclé
	 * @param synonyms tout les synonymes correspondand au motclé
	 */
	public void addAllSynonym(String[] synonyms) {
		for (String synonym : synonyms) {
			addWordSynonym(synonym);
		}
	}

	public String toString() {
		String result = super.toString() + "synonyms: ";
		for (String string : synonyms) {
			result += (string + " ");
		}
		return result;
	}

}
