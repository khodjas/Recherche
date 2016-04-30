package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import core.SpiderLeg;
import log.LoggerUtility;
/**
 * 
 * Classe qui permettra de faire l'algorithme des mots les plus récurrents
 *
 */
public class Search {
	private File file;
	private Interdit interdit;
	private ArrayList<Word> words;
	private static Logger logger = LoggerUtility.getLogger(SpiderLeg.class);


	public Search(String filename) {
		interdit = new Interdit();
		file = new File(filename);
		words = new ArrayList<Word>();
	}

	public ArrayList<Word> getWords() throws NoElementListException {
		if(words.isEmpty()){
			throw new NoElementListException();
		}
		return words;
	}
	/**
	 * Entre les différents mot rencontré si le mot appartient deja
	 * on augmente son occurrence
	 * on l'ajoute sinon
	 * @param word
	 * @return
	 * @throws NullPointerException
	 */
	public boolean addWord(String word) throws NullPointerException{
		word = interdit.ignore(word);
		if(word.isEmpty()){
			throw new NullPointerException();
		}
		if (interdit.verify(word) == false) {

			// si c'est le premier mot
			if (words.size() == 0) {
				words.add(new Word(word));
				return true;
			}
			// sinon
			else {
				for (int index = 0; index < words.size(); index++) {
					// si le mot est égale au mot courant
					if (words.get(index).equals(new Word(word))) {

						words.get(index).setOccurence();
						return true;

					}

				}
				// sinon c'est que le mot n'a pas été retrouvée
				words.add(new Word(word));
				return true;
			}

		}
		return false;
	}
	/**
	 * Récupère les mots enregistrer dans le fichier textuelle et les place
	 * dans une liste
	 */
	public void addText(){
		String line;
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			while ((line = bReader.readLine()) != null) {
				try{
				addWord(line);
				}catch(NullPointerException npe){
					
				}
			}
			bReader.close();

		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
	}
	/**
	 * Parcours la liste de tout les mots du site afin d'obtenir le mot 
	 * ayant la plus hautre occurence
	 * @return le mot le plus récurrent
	 * @throws NoElementListException
	 */
	public Word getCurrentWord() throws NoElementListException {
		Word motRecurrent = null;
		ArrayList<Word> words;
			words = getWords();

			for (Word word : words) {
				if (motRecurrent == null) {
					motRecurrent = word;
				} else if (motRecurrent.getOccurence() <= word.getOccurence()) {
					motRecurrent = word;
				}
			}
		
		return motRecurrent;
	}
	/**
	 * va permettre de supprimer la liste des mots rencontrés
	 */
	public void erase() {
		try {
			getWords().clear();
		} catch (NoElementListException e) {

			logger.warn(e.getMessage());
		}
	}

}
