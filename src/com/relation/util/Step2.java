package com.relation.util;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.realtion.models.GoogleResults;
import com.realtion.models.Sites;

public class Step2 implements IStep {

	@Override
	public void doStep() {
		int maxUrl = 10;
		int countUrl = 0;
		GoogleResults gr = new GoogleResults();
		Sites si = new Sites();
		gr = (GoogleResults) new Persistance().loadObject(gr.getClass().getSimpleName());
		if (gr == null) {
			return;
		}

		if (gr.newUrls.size() < 1) {
			return;
		}
		si = (Sites) new Persistance().loadObject(si.getClass().getSimpleName());
		if (si == null) {
			si = new Sites();
		}

		String url = gr.newUrls.get(1);

		si.addSite(url);
		MySpider spider = new MySpider();
		List<String> newPages = spider.search(url);
		HashMap<String, String> urlRes = spider.getHtmlDocuments();
		for (String sKey : urlRes.keySet()) {
			si.addPage(url, sKey, urlRes.get(sKey));
			System.out.println("Putting" + url + " " + sKey + " " + urlRes.get(sKey));
		}
		for (String newurl : newPages) {
			countUrl++;
			if (countUrl < maxUrl) {
				spider.search(newurl);
				urlRes = spider.getHtmlDocuments();
				for (String sKey : urlRes.keySet()) {
					si.addPage(url, sKey, urlRes.get(sKey));
					System.out.println("Putting" + url + " " + sKey + " " + urlRes.get(sKey));
				}
			}
		}

		new Persistance().saveObject(si);
		return;
	}
}
