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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.relation.models.GoogleResults;
import com.relation.models.Pages;
import com.relation.models.Sites;
import com.relation.util.FileUtils;
import com.relation.util.IStep;
import com.relation.util.Persistance;

public class Step3_NamesToUnique implements IStep {

	// ************************************************************************
	@Override
	public void doStep() {
		Set<String> readLines = new HashSet<String>();
		Set<String> uniqueNames = new HashSet<String>();
		readLines = new Persistance().readNameFile("firstname.txt");
		uniqueNames = processLines(readLines);
		new Persistance().writeNameFile("firstname_unique.txt", uniqueNames);
		return;
	}

	// ************************************************************************
	public Set<String> processLines(Set<String> lines) {
		Set<String> names = new HashSet<String>();
		for (String line : lines) {
			line = line.replaceAll(" ", "");
			if (line.contains("/")) {
				String[] sa = line.split("/");
				for (String s : sa) {
					names.add(s);
				}
			} else {
				names.add(line);
			}
		}
		return names;
	}

	// ************************************************************************
	@Override
	public void setParams(String paramName, String paramValue) {
		// TODO Auto-generated method stub
	}
	// ************************************************************************
}
