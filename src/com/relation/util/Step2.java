package com.relation.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.realtion.models.GoogleResults;
import com.realtion.models.Person;
import com.realtion.models.Sites;

public class Step2 implements IStep {

	@Override
	public void doStep() {
		// Open the file
		FindPerson find = new FindPerson();
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
				System.out.println("HTML DOCS ");
				for (String hashUrl : spider.htmlDocuments.keySet()) {
					System.out.println("key: " + hashUrl + " " + spider.htmlDocuments.get(hashUrl));
					String stext = spider.htmlDocuments.get(hashUrl);
					stext = stext.replace("\n", "").replace("\r", "");
					ArrayList<Person> persons = (ArrayList<Person>) find.getPersons(spider.htmlDocuments.get(hashUrl));
					if(persons.size()< 2){ // only pages with 2 or more persons are important
						//spider.htmlDocuments.remove(hashUrl);
						spider.urlsMap.remove(hashUrl);
					}
					 
					for (Person p : persons) {
						// save to person
						String sp = p.personId + "|" + p.lastName + "|" + p.midName + "|" + p.firstName + "\r\n";
						Files.write(Paths.get("textfiles/persons.txt"), sp.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
						// save to person_rel
						sp = hashUrl + "|" + p.personId + "\r\n";
						Files.write(Paths.get("textfiles/rel_pers.txt"), sp.getBytes(), StandardOpenOption.CREATE,
								StandardOpenOption.APPEND);
					}
				}
				System.out.println("urlsMap   ");
				String out="";
				for (String s : spider.urlsMap.keySet()) {
					System.out.println("" + s + "|" + spider.urlsMap.get(s)  );
					out = s + "|" + spider.urlsMap.get(s) + "\r\n";
					Files.write(Paths.get("textfiles/urls.txt"), out.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
				}
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
