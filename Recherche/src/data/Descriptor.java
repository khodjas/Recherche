package data;

import java.util.ArrayList;

public class Descriptor {
	private ArrayList<KeyWord> keywords;
	private ArrayList<Site> sites;

	public Descriptor() {
		keywords = new ArrayList<KeyWord>();
		sites = new ArrayList<Site>();
	}


	/**
	 * vérifie si le word entrée est bien définie dans le descriptor
	 * 
	 * @param word
	 */
	public boolean containsWord(String word) {
		KeyWord keyWord=new KeyWord(word);
		return keywords.contains(keyWord);
	}
	
	/**
	 * 
	 * @return la liste des sites du Descriptor
	 */
	public ArrayList<Site> getSites(){
		return sites;
	}
	
	public ArrayList<KeyWord> getKeyWords() {
		return keywords;
	}
	
	public static void main(String[] args){
		CreateDescriptor descriptor=new CreateDescriptor();
		descriptor.addKeyWord(new KeyWord("bonjour"));
		descriptor.addKeyWord(new KeyWord("hello"));
		descriptor.addKeyWord(new KeyWord("hola"));
		descriptor.addKeyWord(new KeyWord(""));
		
		descriptor.addSite(new Site("www.sava.com"));
		descriptor.addSite(new Site("www.heee.com"));
		descriptor.addSite(new Site("www.haha.com"));
		descriptor.addSite(new Site("www.non.com"));
		
		Descriptor descriptor2=descriptor;
		ArrayList<Site> sites;
		try{
		if(descriptor2.containsWord("n")){
		sites=descriptor2.getSites();
		for(Site site :sites){
			System.out.println(site.toString());
			}
		}
		}
		catch(NullPointerException e){
			System.err.println(e.getMessage());
		}
		
		
		
	}


	

}
