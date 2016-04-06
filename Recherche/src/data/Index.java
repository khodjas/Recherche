package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import core.SearchSite;
import log.LoggerUtility;

public class Index {
	private ArrayList<Descriptor> descriptors;

	private static Logger logger=LoggerUtility.getLogger(SearchSite.class);
	
	public Index() {
		descriptors = new ArrayList<Descriptor>();
	}

	public ArrayList<Descriptor> getDescriptors() {
		return descriptors;
	}

	public Descriptor getSpecificDescriptor(String keyword) {
		Word word=new Word(keyword);
		Descriptor descriptor2 = new Descriptor(word);
		try{
		for (int index = 0; index < descriptors.size(); index++) {
			if (descriptors.get(index).compare(descriptor2)) {

				return descriptors.get(index);
			}

		}
		}catch(NullPointerException npe){
			logger.warn("aucun site concernant le mot "+keyword);
			
		}
		return descriptor2;
	}

	public void load(String fileName) {
		ObjectInputStream stream;
		try {
			stream = new ObjectInputStream(new FileInputStream(fileName));
			Descriptor descriptor;
			while ((descriptor = (Descriptor) stream.readObject()) != null) {
				descriptors.add(descriptor);
			}
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("l'enregistrement est terminé");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}

	}

	public static void main(String[] args) {
		CreateIndex index = new CreateIndex();

		index.load("fichier.ser");
		System.out.println(index.getDescriptors().get(0).getSites().get(0).getUrl());
		for (Descriptor descripto : index.getDescriptors()) {
			System.out.println(descripto.getSites().get(0).getWord().getOccurence());

		}
	}

}
