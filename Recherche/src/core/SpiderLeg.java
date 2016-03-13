package core;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import data.CrawlException;

public class SpiderLeg {
	
	String user_agent="Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36";
	private List<String> links = new LinkedList<String>();
	private Document htmlDocument;

	/**
	 * 
	 * @param url
	 */
	public boolean crawl(String url) throws CrawlException{
		try {
			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
			Document htmlDocument = connection.get();
			this.htmlDocument = htmlDocument;
			if (connection.response().statusCode() == 200) {
				System.out
						.println("\n **Visiting** Received web page at " + url);
			}
			if (!connection.response().contentType().contains("text/html")) {
				System.out
						.println("**Failure** Retrieved something other than HTML");
				throw new CrawlException();
			}

			Elements linksOnPage = htmlDocument.select("a[href]");
			System.out.println("Found (" + linksOnPage.size() + ") links");

			for (Element link : linksOnPage) {
				this.links.add(link.absUrl("href"));
			}
			return true;
		} catch (IOException ioe) {
			return false;
		}
		catch(IllegalArgumentException iae){
			System.err.println(iae.getMessage()+"url non valide");
			return false;
		}

	}

	/**
	 * 
	 * @param searchWord
	 * @return
	 */
	public String foundWords() throws CrawlException{
		if (this.htmlDocument == null) {
			throw new CrawlException();
		}
		String bodyText = this.htmlDocument.body().text().toLowerCase();
		return bodyText;
	}

	public List<String> getLinks() {
		return this.links;
	}

}
