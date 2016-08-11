package com.relation.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.realtion.models.GoogleResults;
import com.realtion.models.Sites;

public class Step2 implements IStep {

	@Override
	public void doStep() {
		// Open the file
		try {
			FileInputStream fstream = new FileInputStream("textfiles/gr.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;

			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				System.out.println(strLine);
				MySpider spider = new MySpider();
				spider.searchSite(strLine);
				//spider.htmlDocuments
				//spider.urlsMap
			}

			// Close the input stream
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// @Override
	// public void doStep() {
	//
	//
	//
	// int maxUrl = 10;
	// int countUrl = 0;
	// GoogleResults gr = new GoogleResults();
	// Sites si = new Sites();
	// gr = (GoogleResults) new
	// Persistance().loadObject(gr.getClass().getSimpleName());
	// if (gr == null) {
	// return;
	// }
	//
	// if (gr.newUrls.size() < 1) {
	// return;
	// }
	// si = (Sites) new Persistance().loadObject(si.getClass().getSimpleName());
	// if (si == null) {
	// si = new Sites();
	// }
	//
	// String url = gr.newUrls.get(1);
	//
	// si.addSite(url);
	// MySpider spider = new MySpider();
	// List<String> newPages = spider.search(url);
	//
	// HashMap<String, String> urlRes = spider.getHtmlDocuments();
	// for (String sKey : urlRes.keySet()) {
	// si.addPage(url, sKey, urlRes.get(sKey));
	// System.out.println("Putting" + url + " " + sKey + " " +
	// urlRes.get(sKey));
	// }
	// for (String newurl : newPages) {
	// countUrl++;
	// if (countUrl < maxUrl) {
	// spider.search(newurl);
	// urlRes = spider.getHtmlDocuments();
	// for (String sKey : urlRes.keySet()) {
	// si.addPage(url, sKey, urlRes.get(sKey));
	// System.out.println("Putting" + url + " " + sKey + " " +
	// urlRes.get(sKey));
	// }
	// }
	// }
	//
	//
	// new Persistance().saveObject(si);
	// return;
	// }

	@Override
	public void setParams(String paramName, String paramValue) {
		// TODO Auto-generated method stub

	}
}
