package com.relation.Steps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.relation.models.GoogleResults;
import com.relation.models.Pages;
import com.relation.models.Person;
import com.relation.models.Relation;
import com.relation.models.Sites;
import com.relation.util.FileUtils;
import com.relation.util.IStep;
import com.relation.util.Persistance;
import com.relation.util.PersonExtractor;

public class Step4_ExtractPersonsFromText implements IStep {

	ArrayList<Person> allPersons = new ArrayList<Person>();
	ArrayList<Relation> allRelations = new ArrayList<Relation>();
	Set<String> uniqueFirstnames = new Persistance().readNameFile("firstname_unique.txt");
	PersonExtractor pext = new PersonExtractor();
	Set<String> persons = new HashSet<String>();
	Set<String> relations = new HashSet<String>();

	// ************************************************************************
	@Override
	public void doStep() {
		if (readLines()) {

			StringBuffer out = new StringBuffer();
			for (Person onePerson : allPersons) {
				out = new StringBuffer();
				out.append("" + FileUtils.getTStamp() + FileUtils.getItemDelim());
				out.append(onePerson.personId + FileUtils.getItemDelim() + onePerson.firstName + FileUtils.getItemDelim() + onePerson.midName
						+ FileUtils.getItemDelim() + onePerson.lastName);
				out.append(FileUtils.getLineDelim());
				persons.add(out.toString());
				// Files.write(Paths.get(FileUtils.getPathTextFiles()+"persons.txt"),
				// out.getBytes(), StandardOpenOption.CREATE,
				// StandardOpenOption.APPEND);
			}
			for (Relation oneRelation : allRelations) {
				out = new StringBuffer();
				out.append(FileUtils.getTStamp() + FileUtils.getItemDelim());
				out.append(oneRelation.locationId + FileUtils.getItemDelim() + oneRelation.personId);
				out.append(FileUtils.getLineDelim());
				relations.add(out.toString());
				// s= r.locationId + FileUtils.getItemDelim() + r.personId;
				// out = ""+FileUtils.getTStamp() + FileUtils.getItemDelim() + s
				// + FileUtils.getLineDelim();
				// Files.write(Paths.get(FileUtils.getPathTextFiles()+"relations.txt"),
				// out.getBytes(), StandardOpenOption.CREATE,
				// StandardOpenOption.APPEND);
			}
			System.out.println("Persons" + persons.size());
			new Persistance().writeResultFile("persons.txt", persons);
			new Persistance().writeResultFile("relations.txt", relations);
		}

		return;
	}

	// ************************************************************************
	private boolean readLines() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(FileUtils.getPathResultFiles() + "urlcontent.txt"));
			for (String line; (line = br.readLine()) != null;) {
				// System.out.println(line);
				processLine(line);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// ************************************************************************
	private void processLine(String line) {
		String[] sarrContentLine = line.split("\\" + FileUtils.getItemDelim());
		if (sarrContentLine.length < 3) {
			return;
		}
		String sContent = sarrContentLine[2];
		String urlId = sarrContentLine[1];
		System.out.println(sContent);
		ArrayList<Person> pers = pext.getPersons(uniqueFirstnames, sContent);
		ArrayList<Relation> rels = new ArrayList<Relation>();
		for (Person p : pers) {
			rels.add(new Relation(p.personId, urlId));
		}
		System.out.println(line);
		System.out.println("Personsize " + pers.size());
		allPersons.addAll(pers);
		allRelations.addAll(rels);
	}

	// ************************************************************************
	@Override
	public void setParams(String paramName, String paramValue) {
		// TODO Auto-generated method stub
	}
	// ************************************************************************
}
