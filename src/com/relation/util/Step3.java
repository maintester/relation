package com.relation.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.realtion.models.GoogleResults;
import com.realtion.models.Pages;
import com.realtion.models.Sites;

public class Step3 implements IStep {

	@Override
	public void doStep() {
		Set<String> names = new HashSet<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(FileUtils.getPathNameFiles() + "firstname.txt"));
			for (String line; (line = br.readLine()) != null;) {
				// System.out.println(line);
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
			Files.write(Paths.get(FileUtils.getPathNameFiles() + "uniquforname.txt"), "".getBytes(),
					StandardOpenOption.CREATE);
			for (String sname : names) {
				sname += FileUtils.getLineDelim();
				Files.write(Paths.get(FileUtils.getPathNameFiles() + "uniqueforname.txt"), sname.getBytes(),
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			}
			// line is not visible here.
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
