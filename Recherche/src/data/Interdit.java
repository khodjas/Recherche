package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * 
 * Classe qui d�finie l'ensemble des restrictions de mots que l'on � besoin
 * non seulement pour le spider mais aussi lors de la recherche d'url
 *
 */
public class Interdit {
	private static String FILE_NAME="restriction.txt";
	private ArrayList<String> strings;
	private File file;
	
	/**
	 * Constructeur
	 */
	public Interdit(){
		this.file=new File(FILE_NAME);
		strings=new ArrayList<String>();
		setStrings();
	}
	/**
	 * r�cup�re l'ensemble des mots que l'on ne veut pas r�cup�rer
	 * depuis le fichier restriction.txt
	 */
	public void setStrings(){
		try {
			BufferedReader reader= new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) != null){
				
				strings.add(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
	}
	/**
	 * 
	 * @param value le String appartenant au site va ignor� tout les caract�res
	 * inutiles
	 * @return la value nettoy�
	 */
	public String ignore(String value){
		String[] stripChar={ ":", ";", ",", ".", "_", "^", "~", "(", ")", "[", "]", "?", "|", ">", "<", "!", "{","}" , "*","&","+","`","#","="};
		for(String string:stripChar){
			value=value.replace(string, "");
			
		}
		/*
		String[] accentedE={"�","�","�","�"};
		String[] accentedA={"�"};
		String[] accentedI={"�","�"};
		String[] accentedU={};
		*/
		return value;
	}
	/**
	 * 
	 * @return l'ensemble des mots interdits
	 */
	public ArrayList<String> getStrings() {
		return strings;
	}
	/**
	 * 
	 * @param value
	 * @return vrai si value est l'un des mots interdit,faux sinon
	 */
	public boolean verify(String value){
		if(strings.contains(value)){
			return true;
		}
		return false;
	}	
}
