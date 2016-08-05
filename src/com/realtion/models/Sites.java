package com.realtion.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public class Sites  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4254730612728324485L;
	public HashMap<String, Pages> sites = new HashMap<String, Pages>();
	
	public void addSite(String url) {
		if(!sites.containsKey(url)){
			sites.put(url, new Pages()); 
		}
	}
	public void addPage(String site, String url, String content) {
		if(!sites.containsKey(site)){
			addSite(site);
		}
		sites.get(site).addPage(url, content);

	}
	public Set<String> getAllSites(){
		return sites.keySet();
	}
	public Pages getAllPages(String site){
		return sites.get(site);
	}
}
