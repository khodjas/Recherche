package data;

public class ValidException extends Exception {
	@Override
	public String getMessage() {
		return ("le site vient d'�tre ajouter");
	}
}
