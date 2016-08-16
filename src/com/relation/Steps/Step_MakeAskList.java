package com.relation.Steps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Set;

import com.relation.models.GoogleResults;
import com.relation.util.FileUtils;
import com.relation.util.GoogleQuery;
import com.relation.util.IStep;

public class Step_MakeAskList implements IStep {

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
				s= s + FileUtils.getItemDelim() + params.get("search") +  FileUtils.getLineDelim();
			    Files.write(Paths.get("textfiles/gr.txt"), s.getBytes() ,  StandardOpenOption.CREATE, StandardOpenOption.APPEND);
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
