package data;

import java.util.ArrayList;

public class Index {
	private ArrayList<Descriptor> descriptors;
	
	public Index(){
		descriptors=new ArrayList<Descriptor>();
	}
	
	public ArrayList<Descriptor> getDescriptors(){
		return descriptors;
	}
	
	public Descriptor getDescriptor(int index){
		
		return descriptors.get(index);
	}
	
	
	public ArrayList<Site> SearchSites(String word){
		int index=0;
		
		return descriptors.get(index).getSites();
	}

}
