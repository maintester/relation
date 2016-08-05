package com.relation.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;



public class NameFinder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	 try {
		new NameFinder().findName();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

	public   void findName() throws IOException {
//		InputStream is = new FileInputStream("en-ner-person.bin");
//	 
//		TokenNameFinderModel model = new TokenNameFinderModel(is);
//		is.close();
//	 
//		NameFinderME nameFinder = new NameFinderME(model);
//	 
//		String []sentence = new String[]{
//				"The",
//			    "Henry",
//			    "Smith",
//			    "is",
//			    "a",
//			    "good",
//			    "person"
//			    };
//	 
//			Span nameSpans[] = nameFinder.find(sentence);
//	 
//			for(Span s: nameSpans)
//				System.out.println(s.toString());			
//		
		
		
	}
}
