package com.relation.main;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Document;

import com.relation.Steps.Step1_AskGoogle;
import com.relation.Steps.Step2_Crawl;
import com.relation.Steps.Step3_NamesToUnique;
import com.relation.Steps.Step4_ExtractPersonsFromText;
import com.relation.Steps.Step5_DatatoDB;
import com.relation.Steps.Step_0_MakeListForGoogle;
import com.relation.models.GoogleResults;
import com.relation.models.World;
import com.relation.util.GoogleQuery;
import com.relation.util.IStep;
import com.relation.util.MySpider;
import com.relation.util.Persistance;
import com.relation.util.Spider;

public class Starter {
	private static String OS = System.getProperty("os.name").toLowerCase();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println(OS);
		 
	 
		//new Step_0_MakeListForGoogle().doStep();
	 
		IStep s1 = new Step1_AskGoogle();
//		s1.setParams("search", "tennis verein bensheim");

		//s1.doStep();
		
		

 //new Step2_Crawl().doStep();
		//	new Step3().doStep();
	 new Step4_ExtractPersonsFromText().doStep();

		// new Step5_DatatoDB().doStep();

		// new Step5_DatatoDB().doStep();


		
//		World myworld = new World();
//		myworld = (World) new Persistance().loadObject("" + myworld.getClass().getSimpleName());
//		if (myworld == null) {
//			myworld = new World();
//			myworld.init();
//			System.out.println("make new world");
//		}
//
	 
		
		
//		Spider spider = new Spider();
//		String sUrl = "http://baden.liga.nu/cgi-bin/WebObjects/TennisLeagueTVR.woa/wa/clubTeams?club=18165";
//		sUrl = "http://www.lebensentscheidung.de/bibel.html";
//		spider.search(sUrl, "");
//		for (String url : spider.getHtmlDocuments().keySet()) {
//			System.out.println("url " + url + " content " + spider.getHtmlDocuments().get(url).body().text());
//
//		}

		 

	}
 

	 

}
