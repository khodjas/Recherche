package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;

import org.omg.CORBA.portable.ValueBase;

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
		if((value.contains("<"))){
			value=value.replaceAll("<", "");
		}
		if(value.contains(">")){
			value=value.replaceAll(">", "");
		}
		if(value.contains("[")||value.contains("]")){
			value=value.replaceAll("[^\\w]", "");
		}
		if(value.contains(".")){
			value=value.replaceAll(".", "");
		}
		if(value.contains(":")){
			value=value.replaceAll(":", "");
		}
		if(value.contains("?")){
		//	value=value.replaceAll("?", "");
		}
		
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