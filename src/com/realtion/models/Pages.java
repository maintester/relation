package com.realtion.models;

import java.io.Serializable;
import java.util.HashMap;

public class Pages  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2769366483373861500L;
	public HashMap<String, String> pages = new HashMap<String, String> ();  

	public HashMap<String, String> getPages() {
		return pages;
	}

	public void addPage(String url, String content){
		pages.put(url, content);
	}
}
