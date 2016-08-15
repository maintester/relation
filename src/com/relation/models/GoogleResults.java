package com.relation.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class GoogleResults  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 107681446979940598L;

	public ArrayList <String> newUrls = new ArrayList <String>();
	public ArrayList  <String> spideredUrls = new ArrayList <String>();
	
	public void addAll(Collection <String> c){
		for(String s : c){
			if(newUrls.contains(s)){}else{
				newUrls.add(s);
			}
		}
		
	}
}
