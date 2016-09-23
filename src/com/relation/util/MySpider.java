package com.relation.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.MimetypesFileTypeMap;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import com.relation.interfaces.IPersistance;
import com.relation.interfaces.ISpider;
import com.relation.util.log.MyLogger;

public class MySpider implements ISpider {
	// private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1;
	// WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112
	// Safari/535.1";
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2";
	private static final int MAX_PAGES_TO_SEARCH = 20;
	private static final String PDF_Name = "convert.pdf";
	private Set<String> pagesVisited = new HashSet<String>();

	public HashMap<String, String> htmlDocuments = new HashMap<String, String>();
	public HashMap<String, String> urlsMap = new HashMap<String, String>();

	private List<String> pagesToVisit = new LinkedList<String>();
	boolean siteMaxReached = false;
	private Set<String> fetchedUrls = null;

	private Connection connection = null;
	private Document htmlDocument = null;
	private String contentType = "";

	private IPersistance persistance = null;
	// use the classname for the logger, this way you can refactor
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// ************************************************************************
	@Override
	public void init(IPersistance ppersistance) {
		try {
			MyLogger.setup();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.setLevel(Level.INFO);
		LOGGER.severe("Info Log");
		LOGGER.warning("Info Log");
		persistance = ppersistance;
		LOGGER.warning("Info Log2");

	}

	// ************************************************************************
	@Override
	public void clear() {
		pagesVisited = new HashSet<String>();
		htmlDocuments = new HashMap<String, String>();
		urlsMap = new HashMap<String, String>();
		pagesToVisit = new LinkedList<String>();
		siteMaxReached = false;
		fetchedUrls = persistance.readUrls();
	}

	// ************************************************************************
	private String cleanDokumentText(String sText) {
		sText = sText.replaceAll("\r", "");
		sText = sText.replaceAll("\n", "");
		sText = sText.replaceAll("\\|", "");
		return sText;

	}

	// ************************************************************************
	@Override
	public void searchSite(String baseurl) {
		pagesToVisit.add(baseurl);
		pagesVisited.add(baseurl);
		while (pagesToVisit.size() > 0) {
			if (pagesToVisit.get(0).contains(baseurl)) {
				System.out.println("call searchurl " + pagesToVisit.get(0));
				searchOneUrl(baseurl, pagesToVisit.get(0));
				pagesVisited.add(pagesToVisit.get(0));
			}
			pagesToVisit.remove(0);
			System.out.println("size " + pagesToVisit.size());
		}

	}

	// ************************************************************************
	@Override
	public boolean fetchDocument(String url) {
		connection = Jsoup.connect(url).timeout(6000).userAgent(USER_AGENT);
		htmlDocument = null;
		try {
			htmlDocument = connection.get();
			contentType = connection.response().contentType();
		} catch (UnsupportedMimeTypeException e1) {
			contentType = e1.getMimeType();
		} catch (Exception e) {
			contentType = "Error";
			return false;
		}

		return true;
	}

	// ************************************************************************
	@Override
	public void searchOneUrl(String baseUrl, String url) {

		String urlHash = Persistance.generateMD5(url);
		if (fetchedUrls.contains(urlHash)) {
			System.out.println("Duplicate " + url);
			return;
		}
		urlsMap.put(urlHash, url);

		 System.out.println("Hole " + url);

		try {
			connection = Jsoup.connect(url).timeout(6000).userAgent(USER_AGENT);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		htmlDocument = null;
		fetchDocument(url);
		if (contentType.contains("text/html")) {
			processHtmlDokument(baseUrl, urlHash);
		}
		if (contentType.contains("application/pdf")) {
			processPdfDokument(url, urlHash);
		}
		return;
	}

	// ************************************************************************
	private void processHtmlDokument(String baseUrl, String urlHash) {
		Elements links = htmlDocument.select("a[href]");
		Elements media = htmlDocument.select("[src]");
		for (Element e : links) {
			String newUrl = e.absUrl("href");
			if (newUrl.contains("#")) {
				newUrl = newUrl.substring(0, newUrl.indexOf("#"));
			}
			// no pictures etc
			if (newUrl.toLowerCase().contains(".jpg") || newUrl.toLowerCase().contains(".png")
					|| newUrl.toLowerCase().contains(".jpeg")) {
				return;
			}
			// only urls on this site
			if (newUrl.indexOf(baseUrl) < 0) {
				return;
			}

			if (newUrl.length() > 1 && !pagesVisited.contains(newUrl) && pagesToVisit.indexOf(newUrl) < 0) {
				// System.out.println("neue url :" +newUrl ) ;
				if (siteMaxReached == false) {
					pagesToVisit.add(newUrl);
					if (pagesToVisit.size() > Limits.CRAWL_MAX_PAGE_PER_SITE) {
						siteMaxReached = true;
					}
				}
			}
		}
		htmlDocuments.put(urlHash, htmlDocument.text());
		String result = cleanDokumentText(htmlDocument.text());

		String sp = "" + FileUtils.getTStamp() + FileUtils.getItemDelim() + urlHash + FileUtils.getItemDelim() + result
				+ FileUtils.getLineDelim();
		Set<String> values = new HashSet<String>();
		values.add(sp);
		persistance.writeResultFile("urlcontent.txt", values);

		return;

	}

	// ************************************************************************
	private void processPdfDokument(String url, String urlHash) {
		Path pa = MySpider.downloadPdf(url, "");
		if (pa == null) {
			return;
		}
		System.out.println("downloading PDF" + url);
		File input = pa.toFile();

		PDDocument pd;
		String result="";
		try {
			pd = PDDocument.load(input);
			PDFTextStripper stripper = new PDFTextStripper();
			result = stripper.getText(pd);
			pd.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		result = result.replaceAll("\r", "");
		result = result.replaceAll("\n", "");
		result = result.replaceAll("\\|", "");
		input = null;
		pa.toFile().delete();
		// htmlDocuments.put(urlHash, result);
		// System.out.println(result);
		htmlDocuments.put(urlHash, result);

		String sp = "" + FileUtils.getTStamp() + FileUtils.getItemDelim() + urlHash + FileUtils.getItemDelim() + result
				+ FileUtils.getLineDelim();
		// Files.write(Paths.get(FileUtils.getPathTextFiles() +
		// "urlcontent.txt"), sp.getBytes(),
		// StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		Set<String> values = new HashSet<String>();
		values.add(sp);
		persistance.writeResultFile("urlcontent.txt", values);

	}

	// ************************************************************************

	// ************************************************************************
	private static String getMimeType(String fileName) {
		System.out.println(fileName);
		// String fileName = "/path/to/file";
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

		// only by file name
		String mimeType = mimeTypesMap.getContentType(fileName);

		return mimeType;

	}

	// ************************************************************************
	public static Path downloadPdf(String sourceUrl, String targetDirectory) {
		Path targetPath = null;
		try {
			URL url = new URL(sourceUrl);
			String fileName = url.getFile();
			fileName = "C_" + new Date().getTime() + "_" + MySpider.PDF_Name;
			targetPath = new File(targetDirectory + fileName).toPath();
			Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return targetPath;
	}

	@Override
	public HashMap<String, String> getHtmlDocuments() {
		return htmlDocuments;
	}

	@Override
	public HashMap<String, String> getUrlsMap() {
		return urlsMap;
	}

	// ***********************************************************
	// public HashMap<String, String> getHtmlDocuments() {
	// return htmlDocuments;
	// }
	// ***********************************************************
}
