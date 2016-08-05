package com.relation.util;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.realtion.models.GoogleResults;
import com.realtion.models.Pages;
import com.realtion.models.Sites;

public class Step3 implements IStep {

	@Override
	public void doStep() {
		
		Sites si = new Sites();
		si = (Sites) new Persistance().loadObject(si.getClass().getSimpleName());
		if (si == null) {
			return;
		}

		for(String s : si.getAllSites()){
			Pages p = si.getAllPages(s);
			 for(String key: p.pages.keySet()){
				 System.out.println("k: "+key + "  p: " +  p.pages.get(key));
			 }
		} 

	 
		return;
	}
}
