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
import com.relation.util.PersonExtractor;

public class Step4 implements IStep {

	@Override
	public void doStep() {
		ArrayList<Person> allPersons = new ArrayList<Person>();
		ArrayList<Relation> allRels= new ArrayList<Relation>() ; 
		Set<String> names = new HashSet<String>();

		try {
			BufferedReader br = new BufferedReader(
					new FileReader(FileUtils.getPathNameFiles() + "firstname_unique.txt"));
			for (String line; (line = br.readLine()) != null;) {
				names.add(line);
				//System.out.println(line);
			}
			PersonExtractor pext = new PersonExtractor();
			BufferedReader brcontent = new BufferedReader(
					new FileReader(FileUtils.getPathTextFiles() + "urlcontent.txt"));
			for (String line; (line = brcontent.readLine()) != null;) {
				 System.out.println(line);
				String[] sarrContentLine = line.split("\\"+FileUtils.getItemDelim());
				String sContent = sarrContentLine[2];
				System.out.println(sContent);
				ArrayList<Person> pers = pext.getPersons(names, sContent);
				ArrayList<Relation> rels = new ArrayList<Relation>();
				for(Person p : pers){
					rels.add(new Relation(p.personId ,sarrContentLine[1] ));
				}
				allPersons.addAll(pers);
				allRels.addAll(rels);
			}
			String out="";
			String s="";
			for (Person p :allPersons){
				s= p.personId  + FileUtils.getItemDelim() + p.firstName +FileUtils.getItemDelim() + p.midName + FileUtils.getItemDelim() + p.lastName ;
				out = ""+FileUtils.getTStamp() + FileUtils.getItemDelim() +  s +FileUtils.getLineDelim();
				Files.write(Paths.get(FileUtils.getPathTextFiles()+"persons.txt"), out.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
				
			}
			for (Relation r :allRels){
				s= r.locationId  + FileUtils.getItemDelim() + r.personId;
				out = ""+FileUtils.getTStamp() + FileUtils.getItemDelim() +  s + FileUtils.getLineDelim();
				Files.write(Paths.get(FileUtils.getPathTextFiles()+"relations.txt"), out.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;
	}

	@Override
	public void setParams(String paramName, String paramValue) {
		// TODO Auto-generated method stub

	}
}
