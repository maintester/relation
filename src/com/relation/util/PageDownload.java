package com.relation.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

public class PageDownload {

    public static void main(String[] args) throws Exception {
        String webUrl = "http://www.tennisverein1975hagenbach.de/j25/";
        URL url = new URL(webUrl);
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        HTMLEditorKit htmlKit = new HTMLEditorKit();
        HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
        HTMLEditorKit.Parser parser = new ParserDelegator();
        HTMLEditorKit.ParserCallback callback = htmlDoc.getReader(0);
        parser.parse(br, callback, true);
System.out.println("teil2"+ htmlDoc.getText(1, 70));
        for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.IMG);
                iterator.isValid(); iterator.next()) {
        	System.out.println("teil2");
        	
        	  AttributeSet attributes = iterator.getAttributes();
              String srcString = (String) attributes.getAttribute(HTML.Attribute.HREF);
              System.out.print(attributes.getAttributeCount());
              int startOffset = iterator.getStartOffset();
              int endOffset = iterator.getEndOffset();
              int length = endOffset - startOffset;
              String text = htmlDoc.getText(startOffset, length);
              System.out.println(" - " + text);
              
 //           AttributeSet attributes = iterator.getAttributes();
//            String imgSrc = (String) attributes.getAttribute(HTML.Attribute.SRC);
//            if (imgSrc != null && (imgSrc.endsWith(".jpg") || (imgSrc.endsWith(".jpeg"))
//                    || (imgSrc.endsWith(".png")) || (imgSrc.endsWith(".ico"))
//                    || (imgSrc.endsWith(".bmp")))) {
////                try {
////                    downloadImage(webUrl, imgSrc);
////                } catch (IOException ex) {
////                    System.out.println(ex.getMessage());
////                }
//            }
        }

    }

    public static Element getCurrentLinkElement(HTMLDocument doc, int pos) {
        Element element2 = null;
        Element element = doc.getCharacterElement(pos);
        Object linkAttribute = null; //elem.getAttributes().getAttribute(HTML.Tag.A);
        Object href = null;
        while (element != null && linkAttribute == null) {
            element2 = element;
            linkAttribute = element.getAttributes().getAttribute(HTML.Tag.A);
            if (linkAttribute != null) {
                href = ((AttributeSet) linkAttribute).getAttribute(HTML.Attribute.HREF);
            }
            element = element.getParentElement();
        }
        if (linkAttribute != null && href != null) {
            return element2;
        }
        else {
            return null;
        }
    }
    
    private static void downloadImage(String url, String imgSrc) throws IOException {
        BufferedImage image = null;
        try {
            if (!(imgSrc.startsWith("http"))) {
                url = url + imgSrc;
            } else {
                url = imgSrc;
            }
            imgSrc = imgSrc.substring(imgSrc.lastIndexOf("/") + 1);
            String imageFormat = null;
            imageFormat = imgSrc.substring(imgSrc.lastIndexOf(".") + 1);
            String imgPath = null;
            imgPath = "" + imgSrc + "";
            URL imageUrl = new URL(url);
            image = ImageIO.read(imageUrl);
            if (image != null) {
                File file = new File(imgPath);
                System.out.println(file.getAbsolutePath());
                ImageIO.write(image, imageFormat, file);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
