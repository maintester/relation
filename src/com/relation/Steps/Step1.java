package com.relation.Steps;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.relation.interfaces.IPersistance;
import com.relation.models.GoogleResults;
import com.relation.util.FileUtils;
import com.relation.util.GoogleQuery;
import com.relation.util.IStep;
import com.relation.util.Limits;
import com.relation.util.Persistance;

public class Step1 implements IStep {

	private HashMap<String, String> params = new HashMap<String, String>();

	private Set<String> searchs = new HashSet<String>();
	private final static Logger logger  = Logger.getLogger(Step1.class.getName());  

	// *************************************************************************
	@Override
	public void doStep() {
		System.out.println("start Step " +new Date().toString());
		logger.log(Level.INFO, "step2");
		GoogleQuery query = new GoogleQuery();
		IPersistance persistance = new Persistance();
		Set<String> sResult = getGoogleData(query, persistance);
		persistance.writeResultFile("gr.txt", sResult);
		System.out.println("stop Step " +new Date().toString());
		return;
	}

	// *************************************************************************
	private Set<String> getGoogleData(GoogleQuery query,IPersistance persistance) {
		int start = Limits.START_IN_GR;
		int stop = Limits.STOP_IN_GR;
		System.out.println("Starting at "+start + "  stoping at" +stop);
		Set<String> result = new HashSet<String>();
		searchs = persistance.readResultFile("searchfor.txt");
		int counter = 0;
		for (String sQuery : searchs) {
			counter++;
			if (counter >= start && counter < stop) {
				System.out.println("Time" +new Date().toString());
				System.out.println("counter "+counter);
				Set<String> sUrls = query.getDataFromGoogle(sQuery);
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				GoogleResults gr = new GoogleResults();
				gr.addAll(sUrls);
				for (String s : gr.newUrls) {
					s = s + FileUtils.getItemDelim() + sQuery + FileUtils.getLineDelim();
					result.add(s);
					//System.out.println(s);
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
