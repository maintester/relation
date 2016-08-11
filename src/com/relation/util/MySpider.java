package com.relation.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.activation.MimetypesFileTypeMap;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class MySpider {
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private static final int MAX_PAGES_TO_SEARCH = 20;
	private static final String PDF_Name = "convert.pdf";
	private Set<String> pagesVisited = new HashSet<String>();

	public  HashMap<String, String> htmlDocuments = new HashMap<String, String>();
	public HashMap<String, String> urlsMap = new HashMap<String, String>();
	
	private List<String> pagesToVisit = new LinkedList<String>();

	public void searchSite(String baseurl) {
		if (baseurl.indexOf("http") == -1) {
			baseurl = "http://" + baseurl;
		}
		pagesToVisit.add(baseurl);
		pagesVisited.add(baseurl);
		while (pagesToVisit.size() > 0) {
			if (pagesToVisit.get(0).contains(baseurl)) {
				searchOneUrl(pagesToVisit.get(0));
				pagesVisited.add(pagesToVisit.get(0));
			}
			pagesToVisit.remove(0);
			// System.out.println("size " +pagesToVisit.size());
		}
		for( String s :htmlDocuments.keySet()){
			System.out.println(htmlDocuments.get(s));
		}
	}

	public void searchOneUrl(String url) {
		String urlHash = Persistance.generateMD5(url);
		urlsMap.put(urlHash, url);

		//System.out.println("Hole " + url);

		Connection connection = Jsoup.connect(url).timeout(6000).userAgent(USER_AGENT);
		Document htmlDocument = null;
		String contentType = "";
		try {
			try {
				htmlDocument = connection.get();
				contentType = connection.response().contentType();
			} catch (UnsupportedMimeTypeException e1) {
				contentType = e1.getMimeType();
			}

			// System.out.println(contentType);

			if (contentType.contains("text/html")) {
				htmlDocument = connection.get();
				// htmlDocuments.put(urlHash, htmlDocument.text());
				// System.out.println("testDerSeite: " +htmlDocument.text());
				Elements links = htmlDocument.select("a[href]");
				Elements media = htmlDocument.select("[src]");
				for (Element e : links) {
					// urls.put( Persistance.generateMD5(e.absUrl("href")),
					// e.absUrl("href"));
					String newUrl = e.absUrl("href");
					if (!pagesVisited.contains(newUrl) && pagesToVisit.indexOf(newUrl) < 0) {
						System.out.println("neue url :" +newUrl ) ;
						pagesToVisit.add(newUrl);
					}
					// System.out.println("urls:text: " + e.html() + " :Inhalt:"
					// + e.absUrl("href"));
				}
				htmlDocuments.put(urlHash, htmlDocument.text());
				return;
			}

			if (contentType.contains("application/pdf")) {
				MySpider.downloadPdf(url, "");
				File input = new File(MySpider.PDF_Name);
				PDDocument pd;
				pd = PDDocument.load(input);
				input = null;
				PDFTextStripper stripper = new PDFTextStripper();
				String result = stripper.getText(pd);
				// htmlDocuments.put(urlHash, result);
				System.out.println(result);
				htmlDocuments.put(urlHash, result);
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	// **********************************************************

	// **********************************************************
	private static String getMimeType(String fileName) {
		System.out.println(fileName);
		// String fileName = "/path/to/file";
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

		// only by file name
		String mimeType = mimeTypesMap.getContentType(fileName);

		return mimeType;

	}

	// **********************************************************
	public static Path downloadPdf(String sourceUrl, String targetDirectory) throws MalformedURLException, IOException {
		URL url = new URL(sourceUrl);
		String fileName = url.getFile();
		fileName = MySpider.PDF_Name;
		Path targetPath = new File(targetDirectory + fileName).toPath();
		Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
		return targetPath;
	}

	// ***********************************************************
	// public HashMap<String, String> getHtmlDocuments() {
	// return htmlDocuments;
	// }
	// ***********************************************************
}
