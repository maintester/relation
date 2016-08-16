package com.relation.Steps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

public class Step5_DatatoDB implements IStep {

	@Override
	public void doStep() {
		//StringBuffer sql = new StringBuffer(); 
		//ArrayList<Person> allPersons = new ArrayList<Person>();
		//ArrayList<Relation> allRels= new ArrayList<Relation>() ; 
		//Set<String> names = new HashSet<String>();
		String dbtable = " wp_relation2u ";
		try {
			// write persons to file
			BufferedReader br = new BufferedReader(
					new FileReader(FileUtils.getPathResultFiles() + "persons.txt"));
			dbtable = " wp_relation2u_person ";
			for (String line; (line = br.readLine()) != null;) {
				//names.add(line);
				//System.out.println(line);
				String[] sa = line.split("\\|");
				
				String s = "";//"Insert into wp_person(personid, val1, val2, val3) values( 'x1','x2','x3','x4');";
				s= "replace into " + dbtable + " set personid= 'x1' , firstname='x2', midname= 'x3', lastname='x4';";
				s= s.replace("x1", sa[1]).replace("x2", sa[2].trim()).replace("x3", sa[3].trim()).replace("x4", sa[4].trim());
				s+= FileUtils.getLineDelim();
				Files.write(Paths.get(FileUtils.getPathResultFiles() + "sql.txt"), s.getBytes(),
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			}
			br.close();
			// write realtions to file
			br = new BufferedReader(
					new FileReader(FileUtils.getPathResultFiles() + "relations.txt"));
			//1471269392243|7ee0192280c1bf363e4a072a17655aae|a1dd8f3d41a6bad6218f6f864d12fbee
			dbtable = " wp_relation2u_relation ";
			for (String line; (line = br.readLine()) != null;) {
				//names.add(line);
				//System.out.println(line);
				String[] sa = line.split("\\|");
				String urlid= sa[1].trim();
				String personid = sa[2].trim();
				String s = "";
				
				String relid =  Persistance.generateMD5(urlid +personid );
				s= "replace into " + dbtable + " set relationid= 'xrelid', urlid = 'xurlid' , personid='xpersonid';";
				s= s.replace("xurlid", urlid ).replace("xpersonid", personid ).replace("xrelid", relid );
				s+= FileUtils.getLineDelim();
				Files.write(Paths.get(FileUtils.getPathResultFiles() + "sql.txt"), s.getBytes(),
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			}
			br.close();
			// write urls to file
			br = new BufferedReader(
					new FileReader(FileUtils.getPathResultFiles() + "urls.txt"));
			dbtable = " wp_relation2u_url ";
			//1471267362153|9e418f62a561d2a645df060cb2341b5c|http://www.wls-ev.de/hafen.html|Berlin  wassersport verein
			for (String line; (line = br.readLine()) != null;) {
				//names.add(line);
				//System.out.println(line);
				String[] sa = line.split("\\|");
				String s = "";
				s= "replace into " + dbtable + " set urlid= 'x1' , url='x2', searchval='x3';";
				s= s.replace("x1", sa[1]).replace("x2", sa[2].trim()).replace("x3", sa[3].trim());
				s+= FileUtils.getLineDelim();
				Files.write(Paths.get(FileUtils.getPathResultFiles() + "sql.txt"), s.getBytes(),
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			}			
			br.close(); 

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
