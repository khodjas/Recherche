package data;

import java.io.Serializable;

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
