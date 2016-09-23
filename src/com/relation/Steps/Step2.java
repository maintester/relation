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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.relation.interfaces.IPersistance;
import com.relation.interfaces.ISpider;
import com.relation.models.GoogleResults;
import com.relation.models.Person;
import com.relation.models.Sites;
import com.relation.util.FileUtils;
import com.relation.util.FindPerson;
import com.relation.util.IStep;
import com.relation.util.Limits;
import com.relation.util.MySpider;
import com.relation.util.Persistance;

public class Step2 implements IStep {

	// ************************************************************************
	@Override
	public void doStep() {
		Set<String> doNotCralwUrl = new HashSet<String>();
		doNotCralwUrl = new Persistance().readTextFile("donotcrawlurls.txt");
		Set<String> grLines = new LinkedHashSet<String>();
		grLines = new Persistance().readResultFile("gr.txt");
		IPersistance per = new Persistance();
		MySpider spider = new MySpider();
		spider.init(per);
		doSpider( per ,spider, grLines, doNotCralwUrl);

	}

	 
	// ************************************************************************
	public void doSpider( IPersistance per ,ISpider spider, Set<String> grLines, Set<String> doNotCrawlUrl) {
		Set<String> urlLines = new HashSet<String>();
		Set<String> urlContent = new HashSet<String>();
		int current = 0;
		int start = Limits.START_IN_GR;
		int stop = Limits.STOP_IN_GR;
		for (String strLine : grLines) {
			current++;
			String[] sa = strLine.split("\\" + FileUtils.getItemDelim());
			//System.out.println("strLine:"+strLine);
			String searchValue = sa[1];
			String site = sa[0];
			if (current < start | current >= stop) {
				continue;
			}
			if (isStringInSet(doNotCrawlUrl, site)) {
				//System.out.println("do not crawl " + site);
			} else {
				System.out.println("crawling " + site);

				spider.clear();
				spider.searchSite(site);
				// System.out.println("HTML DOCS ");
				for (String hashUrl : spider.getHtmlDocuments().keySet()) {
					String stext = spider.getHtmlDocuments().get(hashUrl);
					stext = stext.replaceAll("\n", " ").replaceAll("\r", " ").replaceAll("\\|", " ").replaceAll("'", " ").replaceAll("/"," ");
					urlContent.add("" + FileUtils.getTStamp() + FileUtils.getItemDelim() + hashUrl + FileUtils.getItemDelim() + stext
							+ FileUtils.getLineDelim());
				}
				per.writeResultFile("urlcontent.txt", urlContent);
				urlContent = new HashSet<String>();
				String out = "";
				for (String sUrlValue : spider.getUrlsMap().keySet()) {
					out = "" + FileUtils.getTStamp() + FileUtils.getItemDelim() + sUrlValue + FileUtils.getItemDelim()
							+ spider.getUrlsMap().get(sUrlValue) + FileUtils.getItemDelim() + searchValue + FileUtils.getLineDelim();
					urlLines.add(out);
				}
				per.writeResultFile("urls.txt", urlLines);
				urlLines = new HashSet<String>();

			}
		}
		return;
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
