package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Spider {

	private static final int MAX_PAGES_TO_SEARCH = 10;
	private static final String filename="testSite.txt";
	private Set<String> pagesVisited = new HashSet<String>();
	private List<String> pagesToVisit = new LinkedList<String>();
	private Search recherche;
	private CreateIndex index;

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

	

	
	public void eraseFile(){
		File file=new File(filename);
		file.delete();
		
	}
	
	public void currentSearch() {

	}
	
	
//
//concernant les probleme il y'en a un qui provient des exceptions ajouter une pour la méthode crawl 	
//	
//et aussi une mauvaise actualisation de la recherche de mots	 (RESOLUE)
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
				String Currentword = recherche.getCurrentWord();
				recherche.erase();
				CreateDescriptor uniqDescriptor = new CreateDescriptor(Currentword);
				uniqDescriptor.addSite(url);
				pagesVisited.add(currentUrl);
				this.pagesToVisit.addAll(leg.getLinks());
				index.inserDescriptor(uniqDescriptor);
				
				

			}catch (NoElementListException nele){
				System.out.println(nele.getMessage());
			}catch (CrawlException ce) {
				System.out.println(ce.getMessage());
			} catch (ValidException ve) {
				// TODO Auto-generated catch block
				System.err.println(ve.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}

			
		}
		System.out.println(String.format("**Done** Visited"
				+ pagesVisited.size() + "web page(s)"));
		System.out.println();
		System.out.println();
		System.out.println();
		index.save("");
		
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

}
