package com.relation.util;

//import edu.stanford.nlp.ie.AbstractSequenceClassifier;
//import edu.stanford.nlp.ie.crf.CRFClassifier;
//import edu.stanford.nlp.io.IOUtils;
//import edu.stanford.nlp.ling.CoreLabel;
//import edu.stanford.nlp.util.Triple;

import java.util.List;

public class NERExample {

//	public static void main(String[] args) throws Exception {
//
//		String serializedClassifier = "nlpmodels/dewac_175m_600.crf.ser.gz"; // "stanford_classifiers/english.all.3class.distsim.crf.ser.gz";
//
//		AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(serializedClassifier);
//
//		String fileContents = IOUtils.slurpFile("test.txt");
//		List<Triple<String, Integer, Integer>> list = classifier.classifyToCharacterOffsets(fileContents);
//		for (Triple<String, Integer, Integer> item : list) {
//			if (item.first.equalsIgnoreCase("I-PER")) {
//				System.out.println(item.first() + ": " + fileContents.substring(item.second(), item.third()));
//			}
//		}
//	}
}