package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import data.CrawlException;
import data.CreateDescriptor;
import data.CreateIndex;
import data.NoElementListException;
import data.Search;
import data.Site;
import data.ValidException;
import data.Word;
import log.LoggerUtility;

public class Spider {

	private static final int MAX_PAGES_TO_SEARCH = 10;
	private static final String filename = "testSite.txt";
	private Set<String> pagesVisited = new HashSet<String>();
	private List<String> pagesToVisit = new LinkedList<String>();
	private Search recherche;
	private CreateIndex index;

	private static Logger logger = LoggerUtility.getLogger(Spider.class);

	public Spider() {
		recherche = new Search("testSite.txt");
		index = new CreateIndex();
	}

	public void save(String[] list) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		for (int index = 0; index < list.length; index++) {
			writer.write(list[index]);
			writer.newLine();
		}
		writer.close();
	}

	public void eraseFile() {
		File file = new File(filename);
		file.delete();

	}

	public void recursiveSearch() {
		index.addDictonnary("dictionnaire.txt");
		try {
			String line;
			BufferedReader bReader = new BufferedReader(new FileReader("sites.txt"));
			while ((line = bReader.readLine()) != null) {
				search(line);
			}
			bReader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
	}

	//
	// concernant les probleme il y'en a un qui provient des exceptions ajouter
	// une pour la méthode crawl
	//
	// et aussi une mauvaise actualisation de la recherche de mots (RESOLUE)
	//
	/**
	 * 
	 * @param url
	 *            - The starting point of the spider
	 * 
	 * @param searchWord
	 *            - The word of string that you are searching for
	 */
	public void search(String url) {

		while (pagesVisited.size() < MAX_PAGES_TO_SEARCH) {
			recherche.erase();
			String currentUrl;
			SpiderLeg leg = new SpiderLeg();

			if (pagesToVisit.isEmpty()) {
				pagesVisited.add(url);

				currentUrl = url;

			} else {
				currentUrl = nextUrl();

			}

			try {
				leg.crawl(currentUrl);
				String allWord = leg.foundWords();
				String[] list = allWord.split(" ");
				save(list);
				recherche.addText();
				eraseFile();
				pagesVisited.add(currentUrl);
				pagesToVisit.addAll(leg.getLinks());
				indexation(currentUrl);
				


			} catch (NoElementListException nele) {
				logger.error(nele.getMessage());
			} catch (CrawlException ce) {
				logger.error(ce.getMessage());
			} catch (ValidException ve) {
				// TODO Auto-generated catch block
				logger.info(ve.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.fatal(e.getMessage());
			}

		}
		logger.info("fin de la visite nombre de page résultant \n" + pagesVisited.size());
		// System.out.println("**Done** Visited"
		// + pagesVisited.size() + "web page(s)");
		index.save("fichier.ser");
		pagesVisited.clear();
		pagesToVisit.clear();
	}

	/**
	 * Returns the next url to visit (in the order that they were found). We
	 * also do a check to make sure this method doesn't return a URL that has
	 * already been visited.
	 * 
	 * @return
	 */
	private String nextUrl() {
		String nextUrl;
		do {
			nextUrl = pagesToVisit.remove(0);
		} while (pagesVisited.contains(nextUrl));
		pagesToVisit.add(nextUrl);
		return nextUrl;
	}

	public void indexation(String currentUrl) throws NoElementListException, ValidException {
		Word currentWord = recherche.getCurrentWord();
		CreateDescriptor uniqDescriptor = new CreateDescriptor(currentWord);
		Site site = new Site(currentUrl, currentWord);
		logger.info("Mot récurrent du site: " + currentWord);
		uniqDescriptor.addSite(site);
		index.inserDescriptor(uniqDescriptor);
	}

	public static void main(String[] args) {
		Spider spider = new Spider();
		spider.search("http://google.com/");
	}
}
