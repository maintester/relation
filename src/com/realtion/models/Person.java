package com.realtion.models;

import java.io.Serializable;

public class Person implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1526213871307882295L;
	
	public Integer personId = 0;
	public String lastName= ""; 
	public String midName= "";
	public String firstName= "";
	public String idHash="";
	private Infos infos= null;
	
	

}
