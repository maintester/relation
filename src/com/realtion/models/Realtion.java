package com.realtion.models;

import java.io.Serializable;

public class Realtion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8083610422962399294L;
	private Integer personId1 = 0;
	private Integer personId2 = 0;

	public Realtion(Integer from, Integer to){
		personId1 = from;
		personId2 = to;
	} 
}
