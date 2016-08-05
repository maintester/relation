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
	private Set<String> pagesVisited = new HashSet<String>();

	private HashMap<String, String> htmlDocuments = new HashMap<String, String>();

	public List<String> search(String url) {
		List<String> pagesToVisit = new LinkedList<String>();
		if(url.indexOf("http")== -1){
			url = "http://" + url;
		}
		System.out.println("Hole " + url);
		Connection connection = Jsoup.connect(url).timeout(6000).userAgent(USER_AGENT);
		Document htmlDocument = null;
		String contentType = "";
		try {
			try {
				htmlDocument = connection.get();
				contentType = connection.response().contentType();
			} catch (UnsupportedMimeTypeException e1) {
				// e1.printStackTrace();
				String type = e1.getMimeType();
				// type = type.substring(type.indexOf("Mimetype="));
				// type = type.substring(0,type.indexOf(","));
				contentType = type;
			}

			System.out.println(contentType);

			if (contentType.contains("text/html")) {
				htmlDocument = connection.get();
				htmlDocuments.put(url, htmlDocument.text());
				Elements links = htmlDocument.select("a[href]");
				Elements media = htmlDocument.select("[src]");
				for (Element e : links) {
					pagesToVisit.add(e.absUrl("href"));
					//System.out.println("urls " + e.html() + " " + e.absUrl("href"));
				}
			}

			if (contentType.contains("application/pdf")) {
				MySpider.download(url, "");
				File input = new File("a.pdf");
				PDDocument pd;
				pd = PDDocument.load(input);
				PDFTextStripper stripper = new PDFTextStripper();
				String result = stripper.getText(pd);
				htmlDocuments.put(url, result);
				System.out.println(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pagesToVisit;
	}

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
	public static Path download(String sourceUrl, String targetDirectory) throws MalformedURLException, IOException {
		URL url = new URL(sourceUrl);

		String fileName = url.getFile();
		fileName = "a.pdf";

		Path targetPath = new File(targetDirectory + fileName).toPath();

		Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

		return targetPath;
	}

	// ***********************************************************
	public HashMap<String, String> getHtmlDocuments() {
		return htmlDocuments;
	}
	// ***********************************************************
}
