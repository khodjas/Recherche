package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Descriptor implements Serializable{
	private ArrayList<String> sites;
	//l'élt keyword est la parite mère du descripteur
		private String keyword;

		public Descriptor(String keyword) {
			sites = new ArrayList<String>();
			this.keyword = keyword;
			
		}
		
		public void addSite(String site){
	//on vérifie que le site n'est pas déjà éxistant		
			if(!sites.contains(site)){
				sites.add(site);
			}
		}
		
		public String getKeyword(){
			return keyword;
		}
		public ArrayList<String> getSites(){
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
		
		
		public void afficheSites(){
			System.out.println(keyword);
			for(String site:sites){
				System.out.println(site);
			}
		}
}
