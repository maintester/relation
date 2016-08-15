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
import java.util.List;
import java.util.Set;

import com.relation.models.GoogleResults;
import com.relation.models.Pages;
import com.relation.models.Sites;
import com.relation.util.FileUtils;
import com.relation.util.IStep;

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
			Files.deleteIfExists(Paths.get(FileUtils.getPathNameFiles() + "firstname_unique.txt"));
			for (String sname : names) {
				sname += FileUtils.getLineDelim();
				Files.write(Paths.get(FileUtils.getPathNameFiles() + "firstname_unique.txt"), sname.getBytes(),
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
