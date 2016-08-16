package com.relation.util;

import java.util.ArrayList;
import java.util.Set;

import com.relation.models.Person;

public class PersonExtractor {

	// ************************************************************************
	public ArrayList<Person> getPersons(Set<String> names, String textline) {
		ArrayList<Person> persons = new ArrayList<Person>();
		String[] parts = textline.split(" ");
		int index = 0;
		for (String part : parts) {
			part = part.trim();
			if (names.contains(part)) {
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
				family= cleanString(family);
				
				System.out.println("I-PER:" + first + ":" + family + ":");
				persons.add(new Person(first,"",family) );
			}
			index++;
		}
		return persons;
	}
	// ************************************************************************
	public String cleanString(String sVal){
		String ret = sVal;
		ret= ret.replaceAll("\"", "");
		ret= ret.replaceAll("\\.", "");
		ret= ret.replaceAll(",", "");
		return ret;
	}

}
