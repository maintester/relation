package com.realtion.models;

import java.io.Serializable;

import java.util.HashMap;



public class DocumentsList implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 3617116561348659362L;
	
	private HashMap  <String, String > htmlDocuments= new HashMap  <String, String >();

	public HashMap<String, String> getHtmlDocuments() {
		return htmlDocuments;
	}

	public void setHtmlDocuments(HashMap<String, String> htmlDocuments) {
		this.htmlDocuments = htmlDocuments;
	}
}
