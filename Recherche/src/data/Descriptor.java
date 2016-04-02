package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Descriptor implements Serializable{
	
	/**
	 * 
	 */
	private ArrayList<Site> sites;
	//l'élt keyword est la parite mère du descripteur
		private Word keyword;

		public Descriptor(Word keyword) {
			sites = new ArrayList<Site>();
			this.keyword = keyword;
			
		}
		
		public void addSite(Site site){
	//on vérifie que le site n'est pas déjà éxistant		
			if(!sites.contains(site)){
				sites.add(site);
			}
		}
		
		public Word getKeyword(){
			return keyword;
		}
		public ArrayList<Site> getSites(){
			return sites;
		}
		
		public boolean compare(Descriptor descriptor){
			if(this.getKeyword().equals(descriptor.getKeyword())){
				return true;
			}
			else{
				return false;
			}
		}
		
		public boolean compareByFamily(){
			
			return true;
		}
		
		
		public void afficheSites(){
			System.out.println(keyword);
			for(Site site:sites){
				System.out.println(site.getUrl());
			}
		}
}
