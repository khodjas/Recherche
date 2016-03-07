package data;

public class Word {
	private int occurence;
	private String value;

	public Word(String value) {
		this.value = value;
		occurence = 1;
	}

	public int getOccurence() {
		return occurence;
	}

	public void setOccurence() {
		occurence++;
	}

	public String getValue() {
		return value;
	}

	public String toString() {
		return "word: " + getValue() + " number of occurence: "
				+ getOccurence();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Word other = (Word) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

}
