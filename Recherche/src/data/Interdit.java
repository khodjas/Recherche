package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Interdit {
	private String FILE_NAME="restriction.txt";
	private ArrayList<String> strings;
	private File file;
	
	
	public Interdit(){
		this.file=new File(FILE_NAME);
		strings=new ArrayList<String>();
		setStrings();
	}
	
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
	
	public String ignore(String value){
		String[] stripChar={ ":", ";", ",", "-", ".", "_", "^", "~", "(", ")", "[", "]", "?", "|", ">", "<", "!", "{","}" , "*","&","+","`","#","="};
		for(String string:stripChar){
			value=value.replace(string, "");
			
		}
		String[] accentedE={"é","è","ê","ë"};
		String[] accentedA={"à"};
		String[] accentedI={"ï","ì"};
		String[] accentedU={};
		
		return value;
	}
	
	public ArrayList<String> getStrings() {
		return strings;
	}

	public boolean verify(String value){
		if(strings.contains(value)){
			return true;
		}
		return false;
	}	
}
