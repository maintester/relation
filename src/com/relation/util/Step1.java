package com.relation.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Set;

import com.realtion.models.GoogleResults;

public class Step1 implements IStep {

	private HashMap<String, String> params = new HashMap<String, String>();

	@Override
	public void doStep() {
		if(!params.containsKey("search")){return;}
		GoogleQuery query = new GoogleQuery();
		Set<String> sUrls = query.getDataFromGoogle(params.get("search"));
		GoogleResults gr = new GoogleResults();
		gr.addAll(sUrls);
		//gr.newUrls.addAll(sUrls);
		System.out.println("---------------");
		for (String s : gr.newUrls) {
			try {
				s+="\r\n";
			    Files.write(Paths.get("textfiles/gr.txt"), s.getBytes(),  StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			}catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
			System.out.println(s);

		}
		//new Persistance().saveObject(gr);
		return;
	}

	@Override
	public void setParams(String paramName, String paramValue) {
		params.put(paramName, paramValue);

	}
}
