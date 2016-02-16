package data;

public class KeyWord {
	private String value;

	public KeyWord(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeyWord other = (KeyWord) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KeyWord [value=" + value + "]";
	}

}
