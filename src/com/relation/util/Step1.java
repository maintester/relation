package com.relation.util;

import java.util.Set;

import com.realtion.models.GoogleResults;

public class Step1 implements IStep {

	@Override
	public void doStep() {
		GoogleQuery query = new GoogleQuery();
		Set<String> sUrls = query.getDataFromGoogle("tennis karlsruhe");
		GoogleResults gr = new GoogleResults();
		gr.addAll(sUrls);
		//gr.newUrls.addAll(sUrls);
		System.out.println("---------------");
		for (String s : gr.newUrls) {
			System.out.println(s);

		}
		new Persistance().saveObject(gr);
		return;
	}
}
