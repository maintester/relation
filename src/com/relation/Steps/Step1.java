package com.relation.Steps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.relation.models.GoogleResults;
import com.relation.util.FileUtils;
import com.relation.util.GoogleQuery;
import com.relation.util.IStep;
import com.relation.util.Limits;
import com.relation.util.Persistance;

public class Step1 implements IStep {

	private HashMap<String, String> params = new HashMap<String, String>();

	private Set<String> searchs = new HashSet<String>();

	// *************************************************************************
	@Override
	public void doStep() {
		GoogleQuery query = new GoogleQuery();
		Set<String> sResult = getGoogleData(query);
		new Persistance().writeResultFile("gr.txt", sResult);
		return;
	}

	// *************************************************************************
	private Set<String> getGoogleData(GoogleQuery query) {
		int start = Limits.START_IN_GR;
		int stop = Limits.STOP_IN_GR;
		Set<String> result = new HashSet<String>();
		searchs = new Persistance().readResultFile("searchfor.txt");
		int counter = 0;
		for (String sQuery : searchs) {
			counter++;
			if (counter >= start && counter < stop) {
				Set<String> sUrls = query.getDataFromGoogle(sQuery);
				GoogleResults gr = new GoogleResults();
				gr.addAll(sUrls);
				for (String s : gr.newUrls) {
					s = s + FileUtils.getItemDelim() + sQuery + FileUtils.getLineDelim();
					result.add(s);
					System.out.println(s);
				}
			}
		}
		return result;
	}

	// *************************************************************************
	@Override
	public void setParams(String paramName, String paramValue) {
		params.put(paramName, paramValue);

	}
}
