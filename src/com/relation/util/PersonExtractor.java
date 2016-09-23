package com.relation.util;

import java.util.ArrayList;
import java.util.Set;

import com.relation.models.Person;

public class PersonExtractor {

	// ************************************************************************
	public ArrayList<Person> getPersons(Set<String> forenames, String textline) {
		// textline = beautifyLine(textline);
		ArrayList<Person> persons = new ArrayList<Person>();
		String[] parts = textline.split(" ");
		int index = 0;
		int max = parts.length;
		boolean havePerson = false;
		for (index = 0; index < max; index++) {

			String part = parts[index];
			part = part.trim();
			if (forenames.contains(part)) {
				havePerson = true;
				String first = part;
				String family = "";
				if (parts.length > index) {
					family = parts[index + 1];
				}
				if (family.length() < 4) {
					if (index > 1) {
						family = parts[index - 1];
					}
				}
				family = cleanString(family);
				if(family.length()  > 2  && "abcefghijklmnopqrstuvwxyz".indexOf(family.substring(0,1).toLowerCase())== -1){
					havePerson = false;
				}
				if (family.length() < 3 ) {
					havePerson = false;
				}
				if( family.length() > 1 && Character.isLowerCase(family.charAt(0))){
					havePerson = false;
				}
				if (havePerson) {
					// System.out.println(family);
					System.out.println("I-PER:" + first + ":" + family + ":");
					persons.add(new Person(first, "", family));
				}
			}
		}
		return persons;
	}

	// ************************************************************************
	public String beautifyLine(String textline) {
		textline = " " + textline + " ";
		textline = textline.replace("/", " ");
		String sRet = "";
		char[] chars = textline.toCharArray();
		for (int i = 1, n = chars.length - 1; i < n; i++) {
			char nextChar = chars[i + 1];
			char prevChar = chars[i - 1];
			char currentChar = chars[i];
			if (Character.isUpperCase(currentChar) == true && prevChar != ' ') {
				sRet = sRet + " "; // vor jedem Großbuchstaben ein leerzeichen,
									// außer Headings in Big letters
			}
			if (isNumber(currentChar) == true && prevChar != ' ') {
				sRet = sRet + " "; // vor jedem Zahl ein leerzeichen,
			}
			sRet = sRet + currentChar;
		}
		return sRet;
	}

	public boolean isNumber(char sVal) {
		return ("0123456789".indexOf(sVal) != -1);
	}

	// ************************************************************************
	public String cleanString(String sVal) {
		String ret = sVal;
		ret = ret.replaceAll("\"", "");
		ret = ret.replaceAll("\\.", "");
		ret = ret.replaceAll(",", "");
		if (ret.contains("@")) {
			ret = "";
		}
		ret = ret.trim();
		return ret;
	}
	// ************************************************************************

}
