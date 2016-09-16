package com.relation.util;

import java.util.ArrayList;
import java.util.Set;

import com.relation.models.Person;
import com.relation.models.Relation;

public class PersonDetector {

	public static void main(String[] args) {
		Set<String> names = new Persistance().readNameFile("firstname_unique.txt");
		PersonDetector persondetect = new PersonDetector(names);
		String sLine = "Mannschaftsprofil                    TC Karlsruhe Rüppurr                            Pos                            Spieler                            WTA-Rang                            DTB-Rang                                1                                        Beck, Annika                                WTA 41                                DTB 4                                2                                        Friedsam, Anna-Lena                                WTA 52                                DTB 10                                3                                        Pereira, Teliana                                WTA 55                               DTB A 6                           4                                        Flipkens, Kirsten                                WTA 60                                DTB A 9                                5                                        Bondarenko, Katerina                                WTA 67                                DTB A 9                                6                                       Siegemund, Laura                                WTA 71";
		persondetect.processLine(sLine);

	}

	private Set<String> vnames = null;

	public PersonDetector(Set<String> pnames) {
		vnames = pnames;
	}

	// ************************************************************************
	private void processLine(String line) {
		String as = "";
		String[] sa = line.split(" ");
		ArrayList sTokens = new ArrayList();
		for (String s : sa) {
			sTokens.add(s);
		}
	}

}
