package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import core.RecoveredSynonym;
import core.SearchSite;
import log.LoggerUtility;

/**
 * 
 * Classe repr�sentant l'index du moteur de recherche
 * 
 */
public class Index {
	/**
	 * descriptors repr�sentant la liste des descripteurs qui repr�senteront
	 * l'ensemble de l'index
	 */
	private ArrayList<Descriptor> descriptors;
	
	private static Logger logger = LoggerUtility.getLogger(SearchSite.class);

	/**
	 *Constructeur 
	 */
	public Index() {
		descriptors = new ArrayList<Descriptor>();
	}

	/**
	 * 
	 * @return descriptors
	 * retourne la liste des descripteurs
	 */
	public ArrayList<Descriptor> getDescriptors() {
		return descriptors;
	}

	/**
	 * 
	 * @param keyword repr�sentant le mot-cl� � retrouver dans l'un des descriptors
	 * @return descriptor le descripteur sp�cifique
	 * @throws NullPointerException si le descriptor n'existe pas
	 */
	public Descriptor getSpecificDescriptor(String keyword) {
		Word word = new Word(keyword);
		Descriptor descriptor2 = new Descriptor(word);

		for (int index = 0; index < descriptors.size(); index++) {
			if (descriptors.get(index).compare(descriptor2)) {

				return descriptors.get(index);
			}

		}
		logger.warn("aucun site concernant le mot "+keyword);
		return descriptor2;

	}
	
	/**
	 * 
	 * @param keyword le mot synonyme
	 * @return r�cup�re le descripteur contenant le mot keyword
	 * @throws NullPointerException si le descripteur n'�xiste pas
	 */
	public Descriptor getSynonymDescriptor(String keyword) throws NullPointerException{
		
		Word word =new Word(keyword);
		Descriptor descriptor= new Descriptor(word);
		for(int index = 0; index < descriptors.size(); index++){
			if (RecoveredSynonym.synonymExist(descriptors.get(index),descriptor)) {
				logger.info("Descripteur synonymes r�sultant: "+descriptors.get(index).getKeyword());
				return descriptors.get(index);
			}
		}
		throw new NullPointerException();
	}
	
	
	/**
	 * 
	 * @param fileName correspondant au fichier.ser qui poss�de toute notre base
	 * de donn�es
	 */
	public void load(String fileName) {
		ObjectInputStream stream;
		try {
			stream = new ObjectInputStream(new FileInputStream(fileName));
			Descriptor descriptor;
			while ((descriptor = (Descriptor) stream.readObject()) != null) {
				descriptors.add(descriptor);
			}
			stream.close();
			logger.info("enregistrement termin�");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn("l'enregistrement vient de se terminer");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("probleme d'objets s�rialis�");
		}

	}

}
