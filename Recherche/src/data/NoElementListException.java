package data;
/**
 * 
 * Classe levant une exception si la liste est vide ou non 
 *
 */
public class NoElementListException extends Exception {
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return ("ERROR! the list is completely empty!");
	}
}
