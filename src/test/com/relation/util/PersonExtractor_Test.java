package test.com.relation.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.relation.models.Person;
import com.relation.util.Persistance;
import com.relation.util.PersonExtractor;

public class PersonExtractor_Test {

	Set<String> names;
	
	@Before 
	public void setup(){
	 names =  new Persistance().readNameFile("firstname_unique.txt");
	}
	
	@Test
	public void testNextWord() {
		PersonExtractor pex = new PersonExtractor();
		String textline =   "Nicolas Berger, Peter Müller, Alan Schwarz    ";
		pex.setTextline(textline);
		String startName = "Alan";
		String lastName="Schwarz";
		System.out.println(pex.nextWord(textline.indexOf(startName)+ startName.length()));
		assertTrue(pex.nextWord(textline.indexOf(startName)+ startName.length()).equalsIgnoreCase(lastName));
		startName = "Nicolas";
		lastName="Berger";
		assertTrue(pex.nextWord(textline.indexOf(startName)+ startName.length()).equalsIgnoreCase(lastName));
	}
	@Test
	public void testPrevWord() {
		PersonExtractor pex = new PersonExtractor();
		String textline =   "Berger Nicolas  , Peter Müller, Schwarz Alan     ";
		pex.setTextline(textline);
		String startName = "Alan";
		String lastName="Schwarz";
		System.out.println(pex.prevWord(textline.indexOf(startName) ));
		assertTrue(pex.prevWord(textline.indexOf(startName)).equalsIgnoreCase(lastName));
		startName = "Nicolas";
		lastName="Berger";
		assertTrue(pex.prevWord(textline.indexOf(startName)).equalsIgnoreCase(lastName));
	}
	
	@Test
	public void testIsName() {
		PersonExtractor pex = new PersonExtractor();
		boolean bResult = false;
		bResult = pex.isLastName("Berger");
		assertTrue(bResult == true);
		bResult = pex.isLastName("Ber4ger");
		assertTrue(bResult == false);
		bResult = pex.isLastName("berger");
		assertTrue(bResult == false);

	}
	
	
	@Test
	public void testExtract() {
		PersonExtractor pex = new PersonExtractor();
		String textline = "Nicolas Reinecke, Peter Müller, Alan Schwarz ";
		ArrayList<Person> alp = pex.getPersons(names, textline);
		//System.out.println(alp.size());
		assertTrue(3 == alp.size());
		//fail("Not yet implemented");
	}
	
	@Test
	public void testBeautify() {
		PersonExtractor pex = new PersonExtractor();
		String textline = "HolgerReinecke/Peter Mueller";
		String newtextline = pex.beautifyLine(textline);
		assertTrue("Holger Reinecke Peter Mueller".equalsIgnoreCase( newtextline));
	}
	
	@Test
	public void testBeautify_NumberinText() {
		PersonExtractor pex = new PersonExtractor();
		String textline = "HolgerReinecke1/Peter Mueller";
		String newtextline = pex.beautifyLine(textline);
		assertTrue("Holger Reinecke 1 Peter Mueller".equalsIgnoreCase( newtextline));
	}

}
