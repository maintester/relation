package com.realtion.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Relation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8083610422962399294L;
	public String personId1 = "";
	public String personId2 = "";
	public Integer strenght = 0;
	private ArrayList locations = new ArrayList();

	public Relation(String from, String to, String location){
		personId1 = from;
		personId2 = to;
		strenght++;
		locations.add(location);
	} 
}
