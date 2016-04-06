package data;
/**
 * 
 * Classe d'exception par rapport � la recherche de site du robot
 *
 */
public class CrawlException extends Exception {
	@Override
	/**
	 * m�thode retournant le message d'erreur du parcours du robot.
	 * @re
	 */
	public String getMessage() {
		// TODO Auto-generated method stub
		return ("ERROR! call crawl() before performing analysis on the document");
	}

}
