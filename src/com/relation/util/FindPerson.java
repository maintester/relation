package com.relation.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.relation.models.Person;

//import edu.stanford.nlp.ie.AbstractSequenceClassifier;
//import edu.stanford.nlp.ie.crf.CRFClassifier;
//import edu.stanford.nlp.ling.CoreLabel;
// 

public class FindPerson {
//	AbstractSequenceClassifier<CoreLabel> classifier = null;
//
//	public FindPerson() {
//		String serializedClassifier = "nlpmodels/dewac_175m_600.crf.ser.gz"; // "stanford_classifiers/english.all.3class.distsim.crf.ser.gz";
//
//		try {
//			classifier = CRFClassifier.getClassifier(serializedClassifier);
//		} catch (ClassCastException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	// ************************************************************************
//	public List<Person> getPersons(String sText) {
//		ArrayList<Person> retList = new ArrayList<Person>();
//
//		List<Triple<String, Integer, Integer>> list = classifier.classifyToCharacterOffsets(sText);
//		for (Triple<String, Integer, Integer> item : list) {
//			if (item.first.equalsIgnoreCase("I-PER")) {
//				System.out.println(item.first() + ": " + sText.substring(item.second(), item.third()));
//				String names = sText.substring(item.second(), item.third());
//				String[] saNames = names.split(" ");
//				Person p = null;
//				if (saNames.length == 2) {
//					p = new Person(saNames[0], "", saNames[1]);
//					retList.add(p);
//				}
//				if (saNames.length == 3) {
//					p = new Person(saNames[0], saNames[1], saNames[2]);
//					retList.add(p);
//				}
//				
//			}
//		}
//
//		return retList;
//	}

}
