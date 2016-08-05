package com.relation.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderLeg
{
    // We'll use a fake USER_AGENT so the web server thinks the robot is a normal web browser.
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<String> links = new LinkedList<String>();
    private Document htmlDocument;
    private HashMap  <String, Document> htmlDocuments = new HashMap<String, Document>();


	public HashMap<String, Document> getHtmlDocuments() {
		return htmlDocuments;
	}


	/**
     * This performs all the work. It makes an HTTP request, checks the response, and then gathers
     * up all the links on the page. Perform a searchForWord after the successful crawl
     * 
     * @param url
     *            - The URL to visit
     * @return whether or not the crawl was successful
     */
    public boolean crawl(String url)
    {
    	if(url.contains(".zip")){return false;}
        try
        {
            Connection connection = Jsoup.connect(url).timeout(6000).userAgent(USER_AGENT);
           
            Document htmlDocument = connection.get();
            this.htmlDocument = htmlDocument;
            htmlDocuments.put(url,  this.htmlDocument);
           
                System.out.println("text " +  htmlDocument.body().text());
            if(connection.response().statusCode() == 200) // 200 is the HTTP OK status code
                                                          // indicating that everything is great.
            {
                System.out.println("\n**Visiting** Received web page at " + url);
            }
            System.out.println(connection.response().contentType());
            if(!connection.response().contentType().contains("text/html"))
            {
                System.out.println("**Failure** Retrieved something other than HTML");
                return false;
            }
            Elements linksOnPage = htmlDocument.select("a[href]");
            System.out.println("Found (" + linksOnPage.size() + ") links");
            for(Element link : linksOnPage)
            {
            	System.out.println("link found " + link.absUrl("href"));
                this.links.add(link.absUrl("href"));
            }
            return true;
        }
        catch(IOException ioe)
        {
            // We were not successful in our HTTP request
        	ioe.printStackTrace();
            return false;
        }
        catch(Exception e){
        	e.printStackTrace();
            // We were not successful in our HTTP request
            return false;
        	
        }
    }


    /**
     * Performs a search on the body of on the HTML document that is retrieved. This method should
     * only be called after a successful crawl.
     * 
     * @param searchWord
     *            - The word or string to look for
     * @return whether or not the word was found
     */
    public boolean searchForWord(String searchWord)
    {
        // Defensive coding. This method should only be used after a successful crawl.
        if(this.htmlDocument == null)
        {
            System.out.println("ERROR! Call crawl() before performing analysis on the document");
            return false;
        }
        System.out.println("Searching for the word " + searchWord + "...");
        String bodyText = this.htmlDocument.body().text();
        System.out.println(bodyText);
        return bodyText.toLowerCase().contains(searchWord.toLowerCase());
    }


    public List<String> getLinks()
    {
        return this.links;
    }


	public void clearDocuments() {
		htmlDocuments.clear();
		
	}

}
