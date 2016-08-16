package com.relation.Steps;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.relation.models.GoogleResults;
import com.relation.models.Person;
import com.relation.models.Sites;
import com.relation.util.FileUtils;
import com.relation.util.FindPerson;
import com.relation.util.IStep;
import com.relation.util.MySpider;
import com.relation.util.Persistance;

public class Step2 implements IStep {

	// ************************************************************************
	@Override
	public void doStep() {
		// Open the file
		// FindPerson find = new FindPerson();
		Set<String> doNotUrl = new HashSet<String>();
		doNotUrl = new Persistance().readTextFile("donotcrawlurls.txt");
		Set<String> grLines = new HashSet<String>();
		grLines = new Persistance().readResultFile("gr.txt");
		Set<String> urlLines = new HashSet<String>();
		urlLines = spider(grLines, doNotUrl);
		new Persistance().writeResultFile("urls.txt", urlLines);
	}
	// ************************************************************************
	private Set<String>spider(Set<String> grLines , Set<String> doNotUrl){
		Set<String> urlLines = new HashSet<String>();
		for (String strLine : grLines) {
			String[] sa = strLine.split("\\" + FileUtils.getItemDelim());
			String searchValue = sa[1];
			String site = sa[0];
			if (isStringInSet(doNotUrl, site)) {
				System.out.println("do not crawl " + site);
			} else {
				System.out.println("crawling " + site);
				MySpider spider = new MySpider();
				spider.searchSite(site);
				//System.out.println("HTML DOCS ");
				for (String hashUrl : spider.htmlDocuments.keySet()) {
					//System.out.println("key: " + hashUrl + " " + spider.htmlDocuments.get(hashUrl));
					String stext = spider.htmlDocuments.get(hashUrl);
					stext = stext.replaceAll("\n", "").replaceAll("\r", "").replaceAll("\\|", "");
				}
				String out = "";
				for (String sUrlValue : spider.urlsMap.keySet()) {
					out = "" + FileUtils.getTStamp() + FileUtils.getItemDelim() + sUrlValue + FileUtils.getItemDelim() + spider.urlsMap.get(sUrlValue)
							+ FileUtils.getItemDelim() + searchValue + FileUtils.getLineDelim();
					urlLines.add(out);
				}
			}
		}
		return urlLines;
	}
	// ************************************************************************
	private boolean isStringInSet(Set<String> stringSet, String value) {
		boolean bRet = false;
		for (String sVal : stringSet) {
			if (value.indexOf(sVal) > 0) {
				bRet = true;
			}
		}
		return bRet;
	}
	// ************************************************************************
	@Override
	public void setParams(String paramName, String paramValue) {
	}
	// ************************************************************************
}
