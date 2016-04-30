package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import core.Spider;
import log.LoggerUtility;

/**
 * Classe qui sert à la créaion de l'index
 * 
 */
public class CreateIndex extends Index {

	private static Logger logger = LoggerUtility.getLogger(Spider.class);

	/**
	 * Constructeur
	 */
	public CreateIndex() {
		super();
	}

	/**
	 * récupère l'ensemble des descripteurs de l'index
	 */
	public ArrayList<Descriptor> getDescriptors() {
		return super.getDescriptors();
	}

	/**
	 * ajout d'un descripteur si il n'éxiste pas, d'un site sinon
	 * 
	 * @param createDesciptor
	 *            le descripteur crée à partir d'un site
	 * @throws ValidException
	 */
	public void inserDescriptor(CreateDescriptor createDesciptor) throws ValidException {
		if (getDescriptors().isEmpty()) {
			getDescriptors().add(createDesciptor);
		} else {
			for (int index = 0; index < getDescriptors().size(); index++) {
				if (getDescriptors().get(index).compare(createDesciptor)) {
					getDescriptors().get(index).addSite(createDesciptor.getUniqSite());
					throw new ValidException();
				}

			}
			getDescriptors().add(createDesciptor);
		}
	}

	/**
	 * enregistre l'index dans le fichier.ser
	 * 
	 * @param fileName
	 *            le nom du fichier à sérialiser
	 */
	public void save(String fileName) {
		ObjectOutputStream stream;
		try {
			stream = new ObjectOutputStream(new FileOutputStream(fileName));
			for (Descriptor descriptor : getDescriptors()) {
				stream.writeObject(descriptor);
			}
			stream.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * ajoute le dictionnaire dans l'index
	 * 
	 * @param fileName
	 *            nom du fichier contenant le dictionnaire
	 */
	public void addDictonnary(String fileName) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String[] buffer;
			String value;
			CreateDescriptor descriptor;
			while ((value = reader.readLine()) != null) {
				buffer = value.split(" ");
				SemanticWord word = new SemanticWord(buffer[0]);
				if (!(buffer[1].equals(""))) {
					String[] family = buffer[1].split(" ");
					for (int index = 1; index < family.length; index++) {
						word.addWord(family[index]);
					}
				}

				if (!(buffer[2].equals(""))) {
					String[] synonyms = buffer[2].split(" ");
					for (int index = 1; index < synonyms.length; index++) {
						word.addWordSynonym(synonyms[index]);
						SemanticWord semanticWord= new SemanticWord(synonyms[index]);
						semanticWord.addAllSynonym(synonyms);
						CreateDescriptor descriptorSynonym = new CreateDescriptor(semanticWord);
						inserDescriptor(descriptorSynonym);
						logger.info(
								"Ajout d'un descripteur synonyme au mot " + word.getValue() + ": " + synonyms[index]);
					}
				}
				descriptor = new CreateDescriptor(word);
				inserDescriptor(descriptor);
			}
			reader.close();
			logger.info("l'enregistrement du dictionnaire est terminé");
		} catch (ValidException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn("erreur lors de l'enregistrement du dictionnaire");
		}
	}

}
