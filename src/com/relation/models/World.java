package com.relation.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class World implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5909537819929325062L;

	private HashMap <Integer, Person>persons ;
	private ArrayList < Relation>relations ;
	private DocumentsList docs ;
	
	public void init() {
		docs = new DocumentsList();
		persons = new HashMap<Integer, Person>();
		relations = new ArrayList<Relation>();
		
		
	}

	public DocumentsList getDocs() {
		return docs;
	}

	public void setDocs(DocumentsList docs) {
		this.docs = docs;
	}
}
