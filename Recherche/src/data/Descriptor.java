package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Descriptor implements Serializable{
	
	/**
	 * 
	 */
	private ArrayList<Site> sites;
	//l'�lt keyword est la parite m�re du descripteur
		private Word keyword;

		public Descriptor(Word keyword) {
			sites = new ArrayList<Site>();
			this.keyword = keyword;
			
		}
		
		public void addSite(Site site){
	//on v�rifie que le site n'est pas d�j� �xistant		
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
