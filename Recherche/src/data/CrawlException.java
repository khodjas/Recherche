package data;
/**
 * 
 * Classe d'exception par rapport à la recherche de site du robot
 *
 */
public class CrawlException extends Exception {
	@Override
	/**
	 * méthode retournant le message d'erreur du parcours du robot.
	 * @re
	 */
	public String getMessage() {
		// TODO Auto-generated method stub
		return ("ERROR! call crawl() before performing analysis on the document");
	}

}
