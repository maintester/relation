package com.relation.Steps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.relation.models.GoogleResults;
import com.relation.util.FileUtils;
import com.relation.util.GoogleQuery;
import com.relation.util.IStep;

public class Step_0_MakeListForGoogle implements IStep {

	private HashMap<String, String> params = new HashMap<String, String>();

	private Set<String> towns = new HashSet<String>();
	private Set<String> activitys = new HashSet<String>();

	@Override
	public void doStep() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(FileUtils.getPathTextFiles() + "towns.txt"));
			for (String line; (line = br.readLine()) != null;) {
				towns.add(line);
			}
			br = new BufferedReader(new FileReader(FileUtils.getPathTextFiles() + "activity.txt"));
			for (String line; (line = br.readLine()) != null;) {
				activitys.add(line);
			}
			String sValue = "";
			for (String town : towns) {
				for (String activ : activitys) {
					if (town.indexOf("(") > 0) {
						town = town.substring(0, town.indexOf("("));
						town = town.trim();
					}
					sValue = town + " " + activ + " verein" + FileUtils.getLineDelim();
					Files.write(Paths.get(FileUtils.getPathResultFiles() + "searchfor.txt"), sValue.getBytes(), StandardOpenOption.CREATE,
							StandardOpenOption.APPEND);
				}
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
		params.put(paramName, paramValue);

	}
}
