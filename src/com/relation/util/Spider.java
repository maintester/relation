package com.relation.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Document;

public class Spider {
	private static final int MAX_PAGES_TO_SEARCH = 20;
	private Set<String> pagesVisited = new HashSet<String>();
	private List<String> pagesToVisit = new LinkedList<String>();
	
    private HashMap  <String, Document> htmlDocuments= new HashMap<String, Document> ();;

	public HashMap<String, Document> getHtmlDocuments() {
		return htmlDocuments;
	}

	/**
	 * Our main launching point for the Spider's functionality. Internally it
	 * creates spider legs that make an HTTP request and parse the response (the
	 * web page).
	 * 
	 * @param url
	 *            - The starting point of the spider
	 * @param searchWord
	 *            - The word or string that you are searching for
	 */
	public void search(String url, String searchWord) {
		while (this.pagesVisited.size() < MAX_PAGES_TO_SEARCH) {
			String currentUrl;
			SpiderLeg leg = new SpiderLeg();
			if (this.pagesToVisit.isEmpty()) {
				currentUrl = url;
				this.pagesVisited.add(url);
			} else {
				currentUrl = this.nextUrl();
			}
			System.out.println("crawling: "+ currentUrl);
			try {
				leg.crawl(currentUrl); // Lots of stuff happening here. Look at the
										// crawl method in
										// SpiderLeg
//			boolean success = leg.searchForWord(searchWord);
//			if (success) {
//				System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentUrl));
//				//break;
//			}
				this.pagesToVisit.addAll(leg.getLinks());
				this.htmlDocuments.putAll(leg.getHtmlDocuments());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			leg.clearDocuments();
			System.out.println("size " + this.pagesToVisit.size());
		}
		System.out.println("\n**Done** Visited " + this.pagesVisited.size() + " web page(s)");
	}

	/**
	 * Returns the next URL to visit (in the order that they were found). We
	 * also do a check to make sure this method doesn't return a URL that has
	 * already been visited.
	 * 
	 * @return
	 */
	private String nextUrl() {
		String nextUrl;
		do {
			nextUrl = this.pagesToVisit.remove(0);
		} while (this.pagesVisited.contains(nextUrl));
		this.pagesVisited.add(nextUrl);
		return nextUrl;
	}

}
