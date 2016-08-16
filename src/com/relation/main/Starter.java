package com.relation.main;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Document;

import com.relation.Steps.Step1;
import com.relation.Steps.Step2;
import com.relation.Steps.Step3;
import com.relation.Steps.Step4;
import com.relation.Steps.Step5_DatatoDB;
import com.relation.Steps.Step_MakeListForGoogle;
import com.relation.models.GoogleResults;
import com.relation.models.World;
import com.relation.util.GoogleQuery;
import com.relation.util.IStep;
import com.relation.util.MySpider;
import com.relation.util.Persistance;
import com.relation.util.Spider;

public class Starter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//new Step_MakeListForGoogle().doStep();
		IStep s1 = new Step1();
//		s1.setParams("search", "tennis verein bensheim");
	//	s1.doStep();

//	 new Step2().doStep();
		//	new Step3().doStep();
	//	new Step4().doStep();
		 new Step5_DatatoDB().doStep();
		
//		World myworld = new World();
//		myworld = (World) new Persistance().loadObject("" + myworld.getClass().getSimpleName());
//		if (myworld == null) {
//			myworld = new World();
//			myworld.init();
//			System.out.println("make new world");
//		}
//
		//MySpider spider = new MySpider();
		//spider.search("https://www.uni-muenster.de/imperia/md/content/publizieren/kurzanleitungen_pdfa.pdf");
//		String sUrl = "http://baden.liga.nu/cgi-bin/WebObjects/TennisLeagueTVR.woa/wa/clubTeams?club=18165";
//		sUrl = "http://www.lebensentscheidung.de/bibel.html";
//		sUrl = "http://www.badischertennisverband.de/cms/iwebs/default.aspx?mmid=11391&smid=38255";
//		sUrl = "http://www.badischertennisverband.de/cms/iwebs/download.aspx?id=100591"; // PDF
//		spider.search(sUrl);
		
		
//		Spider spider = new Spider();
//		String sUrl = "http://baden.liga.nu/cgi-bin/WebObjects/TennisLeagueTVR.woa/wa/clubTeams?club=18165";
//		sUrl = "http://www.lebensentscheidung.de/bibel.html";
//		spider.search(sUrl, "");
//		for (String url : spider.getHtmlDocuments().keySet()) {
//			System.out.println("url " + url + " content " + spider.getHtmlDocuments().get(url).body().text());
//
//		}

		// myworld = new Starter().doStep1(myworld);
		// myworld = new Starter().doStep2(myworld);

		//new Persistance().saveObject(myworld);

		// String s = "abc";
		// new Persistance().saveObject(s);

	}

	public World doStep2(World myworld) {
		// String oneurl =
		// "http://www.badischertennisverband.de/cms?mmid=11534&smid=38475";
		// String sText = myworld.getDocs().getHtmlDocuments().get(oneurl);
		// System.out.println(sText);
		// System.out.println("Size " +
		// myworld.getDocs().getHtmlDocuments().keySet().size());
		for (String url : myworld.getDocs().getHtmlDocuments().keySet()) {
			System.out.println(url);
			// System.out.println(myworld.getDocs().getHtmlDocuments().get(url));
			// System.out.println("------------------------------------");
		}
		// http://www.badischertennisverband.de/cms?mmid=11534&smid=38475
		return myworld;
	}

	public World doStep1(World myworld) {

		// Schritt 1
		// Schritt 1
		GoogleQuery query = new GoogleQuery();
		Set<String> sUrls = query.getDataFromGoogle("tennis karlsruhe pdf");
		Spider spider = new Spider();
		for (String sUrl : sUrls) {
			spider = new Spider();
			System.out.println("Spidering " + sUrl);
			spider.search("http://" + sUrl, "");
			for (String url : spider.getHtmlDocuments().keySet()) {
				System.out.println("url " + url + " content " + spider.getHtmlDocuments().get(url).body().text());
				myworld.getDocs().getHtmlDocuments().put(url, spider.getHtmlDocuments().get(url).body().text());

			}

		}
		return myworld;

	}

}
