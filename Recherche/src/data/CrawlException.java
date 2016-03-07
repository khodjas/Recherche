package data;

public class CrawlException extends Exception{
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return ("ERROR! call crawl() before performing analysis on the document");
	}
	
}
