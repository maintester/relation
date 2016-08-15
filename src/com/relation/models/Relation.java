package com.relation.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Relation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8083610422962399294L;
	public String personId = "";
	public String locationId="";


	public Relation(String person , String location){
		personId = person;
		locationId = location;
	} 
}
