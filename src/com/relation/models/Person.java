package com.relation.models;

import java.io.Serializable;

import com.relation.util.Persistance;

public class Person implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1526213871307882295L;
	
	public String personId = "";
	public String lastName= ""; 
	public String midName= "";
	public String firstName= "";

	public Person(String firstName, String midName, String lastName){
		this.firstName = firstName;
		this.midName = midName;
		this.lastName = lastName;
		this.personId= Persistance.generateMD5(firstName+"*"+midName+"*"+lastName);
	}
	
}
