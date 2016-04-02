package data;


import java.util.ArrayList;

public class FamilyWord extends Word {
	private ArrayList<String> family;

	public FamilyWord(String value) {
		super(value);
		family = new ArrayList<String>();
	}

	public ArrayList<String> getFamily() {
		return family;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if ((getClass() != obj.getClass())){
			if( getClass().getSuperclass() != obj.getClass()) {
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

	public static void main(String[] args) {
		

	}

}
