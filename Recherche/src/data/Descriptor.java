package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Descriptor implements Serializable{
	private ArrayList<String> sites;
	//l'�lt keyword est la parite m�re du descripteur
		private String keyword;

		public Descriptor(String keyword) {
			sites = new ArrayList<String>();
			this.keyword = keyword;
			
		}
		
		public void addSite(String site){
	//on v�rifie que le site n'est pas d�j� �xistant		
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
