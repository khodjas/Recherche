package data;

import java.io.Serializable;
/**
 * 
 * Classe correspondant au site contenant un couple url/motclé
 *
 */
public class Site implements Serializable{
	private String url;
	private Word word;
	
	public Site(String url,Word word){
		this.url=url;
		this.word=word;
	}

	public String getUrl() {
		return url;
	}

	public Word getWord() {
		return word;
	}
	
	
	
	
	
	
}
