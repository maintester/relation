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

public class Step5_DatatoDB implements IStep {

	@Override
	public void doStep() {
		StringBuffer sql = new StringBuffer(); 
		ArrayList<Person> allPersons = new ArrayList<Person>();
		ArrayList<Relation> allRels= new ArrayList<Relation>() ; 
		Set<String> names = new HashSet<String>();

		try {
			// write persons to file
			BufferedReader br = new BufferedReader(
					new FileReader(FileUtils.getPathTextFiles() + "persons.txt"));
			for (String line; (line = br.readLine()) != null;) {
				//names.add(line);
				//System.out.println(line);
				String[] sa = line.split("\\|");
//				REPLACE INTO `transcripts`
//				SET `ensembl_transcript_id` = 'ENSORGT00000000001',
//				`transcript_chrom_start` = 12345,
//				`transcript_chrom_end` = 12678;
				String s = "";//"Insert into wp_person(personid, val1, val2, val3) values( 'x1','x2','x3','x4');";
				s= "REPLACE into wp_person set personid= 'x1' , val1='x2', val2= 'x3', val3='x4'";
				s= s.replace("x1", sa[1]).replace("x2", sa[2].trim()).replace("x3", sa[3].trim()).replace("x4", sa[4].trim());
				s+= FileUtils.getLineDelim();
				Files.write(Paths.get(FileUtils.getPathTextFiles() + "sql.txt"), s.getBytes(),
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			}
			// write realtions to file
			br = new BufferedReader(
					new FileReader(FileUtils.getPathTextFiles() + "relations.txt"));
			for (String line; (line = br.readLine()) != null;) {
				//names.add(line);
				//System.out.println(line);
				String[] sa = line.split("\\|");
//				REPLACE INTO `transcripts`
//				SET `ensembl_transcript_id` = 'ENSORGT00000000001',
//				`transcript_chrom_start` = 12345,
//				`transcript_chrom_end` = 12678;
				String s = "";
				s= "REPLACE into wp_person set urlid= 'x1' , personid='x2'";
				s= s.replace("x1", sa[1]).replace("x2", sa[2].trim());
				s+= FileUtils.getLineDelim();
				Files.write(Paths.get(FileUtils.getPathTextFiles() + "sql.txt"), s.getBytes(),
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			}
			// write urls to file
			br = new BufferedReader(
					new FileReader(FileUtils.getPathTextFiles() + "urls.txt"));
			for (String line; (line = br.readLine()) != null;) {
				//names.add(line);
				//System.out.println(line);
				String[] sa = line.split("\\|");
//				REPLACE INTO `transcripts`
//				SET `ensembl_transcript_id` = 'ENSORGT00000000001',
//				`transcript_chrom_start` = 12345,
//				`transcript_chrom_end` = 12678;
				String s = "";
				s= "REPLACE into wp_person set urlid= 'x1' , val1='x2', val2='x3'";
				s= s.replace("x1", sa[1]).replace("x2", sa[2].trim()).replace("x3", sa[3].trim());
				s+= FileUtils.getLineDelim();
				Files.write(Paths.get(FileUtils.getPathTextFiles() + "sql.txt"), s.getBytes(),
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
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
