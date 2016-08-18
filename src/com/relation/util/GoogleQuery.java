package com.relation.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.relation.interfaces.IGoogleQuery;



public class GoogleQuery implements IGoogleQuery{

  private static Pattern patternDomainName;
  private Matcher matcher;
  private static final String DOMAIN_NAME_PATTERN 
	= "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
  static {
	patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
  }
	
  public static void main(String[] args) {

	GoogleQuery obj = new GoogleQuery();
	Set<String> result = obj.getDataFromGoogle("tennisverein mannheim");
	for(String temp : result){
		System.out.println(temp);
	}
	System.out.println(result.size());
  }

  public String getDomainName(String url){
		
	String domainName = "";
	matcher = patternDomainName.matcher(url);
	if (matcher.find()) {
		domainName = matcher.group(0).toLowerCase().trim();
	}
	return domainName;
		
  }
 @Override	
  public Set<String> getDataFromGoogle(String pquery) {
	String query = pquery;
	Set<String> result = new HashSet<String>();	
	String request = "";
	
	 
		//request = "https://www.google.com/search?q=" +  URLEncoder.encode(query,"ISO_8859-15")   + "&num=5";
		//"Cp1252"
	//query= query.replaceAll("  ", " ");
	//query= query.replaceAll(" ", " ");
	query= query.replaceAll("Ä", "%C3%84");
	query= query.replaceAll("ä", "%C3%A4");
	query= query.replaceAll("Ö", "%C3%96");
	query= query.replaceAll("ö", "%C3%B6");
	query= query.replaceAll("Ü", "%C3%9C");
	query= query.replaceAll("ü", "%C3%bC");
	//query=""
	request = "http://www.google.com/search?q=" + query  + "&num=5";
	//request ="https://www.bing.com/search?q="+ query;
	//https://duckduckgo.com/search?q=' + query,
	System.out.println("Sending request..." + request);
	 
	try {

		// need http protocol, set this as a Google bot agent :)
		String agent = "Mozilla/5.0 (iPhone; CPU iPhone OS 5_0 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A334 Safari/7534.48.3";
		agent=  "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
		Document doc = Jsoup
			.connect(request)
			.userAgent(agent)
			.timeout(5000).get();

		// get all links
		System.out.println(doc.text());
		Elements links = doc.select("a[href]");
		for (Element link : links) {

			String temp = link.attr("href");
			
			if(temp.startsWith("/url?q=")){
				System.out.println(temp);
                                //use regex to get domain name
				result.add(getDomainName(temp));
			}

		}

	} catch (IOException e) {
		e.printStackTrace();
	}
		
	return result;
  }

}