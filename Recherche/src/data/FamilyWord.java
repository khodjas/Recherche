package data;

import java.util.ArrayList;

/**
 * 
 * Classe fille de Word
 */
public class FamilyWord extends Word {
	/**
	 * liste des mots correspondant à la famille du mot clé
	 */
	private ArrayList<String> family;

	/**
	 * Constructeur
	 * 
	 * @param value
	 *            Mot-clé principale du descriptor
	 */
	public FamilyWord(String value) {
		super(value);
		family = new ArrayList<String>();
	}

	/**
	 * retourne l'ensemble des mots de la famille
	 * 
	 * @return family
	 */
	public ArrayList<String> getFamily() {
		return family;
	}

	/**
	 * amélioration du equals qui compare aussi si le mot-clef correspond à la
	 * famille de mot
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
				return false;
			}
		}
		Word other = (Word) obj;
		if (super.getValue() == null) {
			if (other.getValue() != null) {
				return false;
			}
		} else if (!getValue().equals(other.getValue()) && !this.getFamily().contains(other.getValue())) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param word
	 *            mot à ajouter dans la family
	 */
	public void addWord(String word) {
		getFamily().add(word);
	}

	public String toString() {

		String result = (super.toString() + " word's family: ");
		for (String string : family) {
			result += (string + " ");
		}

		return result;

	}

}
