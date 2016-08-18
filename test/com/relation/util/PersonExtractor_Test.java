package com.relation.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Test;

import com.relation.models.Person;

public class PersonExtractor_Test {

	
	Set<String> names = new Persistance().readNameFile("firstname_unique.txt");
	@Test
	public void testExtract() {
		PersonExtractor pex = new PersonExtractor();
		String textline = "Home  Golf.de	 Golf.de DGV Golf.de Services Stellenmarkt Newsletter Wetter Hintergrundbild Golf.de Konto Golfclubs Produkte für Golfclubs Gewinnspiel-Sieger Turnierkalender Alle Turniere Turnieranmeldungen Turnierergebnisse Handicap-Abfrage Spielvorgabenrechner Hole in One Club mygolf Login Golf.de Shop Bilder Videos Golf.de Deutscher Golf Verband Tournews Tournews Spielersuche European Tour PGA Tour Ladies European Tour LPGA Champions Tour Challenge Tour Pro Golf Tour Specials Masters Tournament US Open British Open PGA Championship Olympische Spiele Ryder Cup Solheim Cup Porsche European Open BMW International Open Winstongolf Senior Open Ranking Tourkalender Leaderboard Olympia 2016 Leaderboard PGA Tour Leaderboard Web.com Tour Neues zum Thema Der unbeirrbare Mr. Wa... Jimmy Walker gewinnt die 98. PGA Championship u... weiterlesen Spitzenergebnis für Ka... Martin Kaymer verbessert sich am Finaltag der P... weiterlesen DGV-Sport DGV-Sport Kramski Deutsche Golf Liga Golf Team Germany #Vorfreude DGV-Wettspiele Lochspielmeisterschaft DM AK 50 / 65 DM AK 35 DMM AK 50 DM Golfer mit Behinderung Internationale Wettspiele German Girls Open German Boys Open IAM AK 50 Team-EMs IAM Herren IAM Damen DGV-Wettspielanmeldung Ranglisten Kinder & Jugend Jungsenioren Senioren Golfer mit Behinderungen Anti-Doping Golfeinstieg Golfeinstieg Erste Schritte Schnuppern Golferlebnis für 19 Euro faszinationgolf.de Platzreife Golf-ABC Interessenten-Datenbank Neues zum Thema Golfregeln in Frage un... Ein Muss für all diejenigen, die bald ihre Plat... weiterlesen Grundwissen für Golfen... Sie wollen die Faszination des Golfsports entde... weiterlesen Regeln Regeln Golfregeln Vorgabensystem Änderungen ab 2016 Amateurstatut Etikette Spielformen Handicap Regelquiz Regeln für Kids Neues zum Thema R&A bietet Decisions-A... Neben der Rules of Golf-App bietet der Royal ... weiterlesen EGA vereinfacht Vorgab... Der Deutsche Golf Verband (DGV) hat sich erfolg... weiterlesen Training Training Am Abschlag Annäherungen Rettungsschläge Kurzes Spiel Putten Grundlagen Mental Strategie & Taktik Fitness & Ernährung Teaching Pros PGA of Germany News DGV-Trainer Unsere Trainer Neues zum Thema Yips sind kein Problem Yips sind funktional, sagt Yips-Forscher Dr. ... weiterlesen 11 Tipps für das Turnier Runter mit dem Handicap! Wir verraten Ihnen elf... weiterlesen Equipment Equipment Nachrichten Expertentipps In the Bag Demodays Test und Kaufberatung Neues zum Thema Titleist Pro V1 - eine... Kein anderer Ball hat das Golfspiel ähnlich bee... weiterlesen Titleist Pro V1: Vom R... Jeden Tag verlassen 300.000 Tileist Pro V1 die ... weiterlesen Plätze Plätze Deutschland International Clubnachrichten Greenfee-Nachrichten Neues zum Thema Auf der Jagd nach Flor... Mit der Honda Classic startet für die Profis tr... weiterlesen Australien: Eine Klass... Von Sydney über Melbourne nach Tasmanien: Wir f... weiterlesen Reisen Reisen Reiseberichte Angebote Hotels Neues zum Thema Der aktuelle Golf.de G... Das Golfgepäck fliegt bei immer weniger Airline... weiterlesen Starke Stimmung! Unser Hintergrundbild für August: Der Lake Cour... weiterlesen Panorama Panorama Panorama News Blogs Tobis #PerfectDrive Ein Münchner auf Reisen Max vor Ort Handicap Papa Mos Roadblog BenZinger Stilfrage Golfevents Ergebnisse & Statistiken Zocks & Spielformen Hintergrundbild Neues zum Thema Golfgott Sommer, Sonne, nörgelnde Kids in der Familienku... weiterlesen Die Handicaps der Stars Golf ist für viele Prominente ein beliebtes Hob... weiterlesen DGV-Services DGV-Services DGV Aufgaben / Ziele Golfentwicklung Vision Gold Mitglieder Gremien Geschäftsstelle Verbandsordnung Vermarktung (DGS) Ausbildung Aktuelle Termine Trainerausbildung Golf-Physio-Coach Golfmanagement Seminarplaner Betrieb Golfanlagen Greenkeeping DGV-Ausweis IT-Services Betriebswirtschaft Beratung Versicherungen Recht & Steuern Marketing Golfmarkt Statistiken Marktforschung Landkarten Jugendarbeit QM Nachwuchsförderung Lucky 33 Grünes Band Umwelt Golf&Natur Biodiversität Bewässerung Pflanzenschutz Downloads (Infoservice) Publikationen Presse DGV-Partnershop DGV-Gewinnspiel Turnierkalender Turnieranmeldung Turnierergebnisse Rio de Janeiro - 5. bis 21. August Olympische Spiele 2016 Golf.de live vor Ort zum Turnierspecial Olympische Spiele Olympia-Gold für Großbritannien 37 Olympische Spiele Ein versöhnlicher Abschluss 39 Eisen Mizuno JPX900: Eisen ist nicht gleich Eisen 35 Mizuno Geschmiedet in Japan, maßgefertigt in Schottland Golf Team Germany Falko Hanisch siegt auf dem 37. Grün Olympische Spiele So laufen die Stechen bei Olympia Olympische Spiele Ein bunter Haufen Fans Großbritannien siegt Gutes Ende Materialfrage Custom-Fabrik Sieg Muirfield Stechen bei Olympia Bunte Fans Olympische Spiele Olympia-Gold für Großbritannien Justin Rose sichert sich in einem packenden Zweikampf mit Schwedens Henrik Stenson die Goldmedaille in der Herrenkonkurrenz bei den Olympischen Spielen von Rio. Matt Kuchar holt Bronze für die USA. weiterlesen Olympische Spiele Ein versöhnlicher Abschluss Martin Kaymer und Alex Cejka spielen am Finaltag in Rio de Janeiro gute Runde... weiterlesen 39 Eisen Mizuno JPX900: Eisen ist nicht g... Mizuno stellt die Eisenserie JPX900 mit den Modellen Hot Metal, Forged und To... weiterlesen 35 Mizuno Geschmiedet in Japan, maßgeferti... Wir sind zu Besuch in der Mizuno Custom-Fabrik im schottischen Cumbernauld un... weiterlesen Golf Team Germany Falko Hanisch siegt auf dem 37. ... Er hat den Titel! Falko Hanisch sichert sich den ganz großen Triumph und setz... weiterlesen Olympische Spiele So laufen die Stechen bei Olympia Pro Golfwettkampf gibt es in Rio nur genau drei Medaillen zu gewinnen. Das ka... weiterlesen Olympische Spiele Ein bunter Haufen Fans Die Zuschauer auf dem Olympic Golf Course in Rio de Janeiro bekennen Farbe un... weiterlesen Newsticker Moore holt fünften Tour-Titel US PGA Tour Langer schiebt sich auf Rang sechs Champions Tour Die Finalrunden live erleben Olympische Spiele Der Start ist gelungen Golfauftakt in Rio Furyk erhält Payne Stewart Award US PGA Tour Wo sind meine Schläger? Olympische Spiele Olympische Spiele Lydia Ko drückt die Daumen 14.08.2016 - Es ging um die Medaillen. Der Finaltag des Olympischen Golfturniers war an Dramatik kaum zu überbieten. Justin Rose und Henrik Stenson lieferten sich einen spannenden Zweikampf. Die Nummer eins der Damen hoffte natürlich auf ein gutes Abschneiden der Neuseeländer.  weiterlesen Olympic Men's Golf Competition Rang Name Nat Loch Par 1 Justin Rose 18 -16 2 Henrik Stenson 18 -14 3 Matt Kuchar 18 -13 4 Thomas Pieters 18 -9 5 Kiradech Aphibarnrat 18 -8 mehr Partner Promotion Silvester in Abu Dhabi 12.08.2016 - Genießen Sie den Jahreswechsel bei perfektem Golfwetter! Lassen Sie sich im luxuriösen 5 Sterne The Westin Abu Dhabi verwöhnen und spielen Sie auf den großartigen Plätzen des Wüstenstaats. weiterlesen Bilder Aktuelle Fotostrecken weitere Fotostrecken > Qualifikation auf dem Golfplatz Kurpfalz 15.08.2016 Herren - Finalrunde 14.08.2016 Fans 13.08.2016 Herren - Runde 3 13.08.2016 Herren - Runde 2 12.08.2016 T7 Wedges (2016/2017) 12.08.2016 JPX900 Eisen (2016/2017) 12.08.2016 Herren - Runde 1 12.08.2016 Plätze und Reise Die schönsten Ziele der Welt weitere Reiseberichte und Platztests > Bärenklöße, Zimtbier, dann Golf Golf gehört in Estland noch nicht zu den Top-Sportarten. Ein paar Tausend Aktive zählt der Staat im Baltikum - Tendenz steigend. An schönen Kursen fehlt es aber nicht. Vor allem um die Sonnwende ist ein Besuch verlockend. weiterlesen Wetter Panorama Bunte Themen rund um den Golfsport weitere Panorama-News > Olympische Spiele Kaymer überwältigt von der Eröffnungs... 06.08.2016 - Martin Kaymer, Ulrich Zilg und Marcus Neumann laufen stellvertretend für Golfdeutschland bei der Eröffnungsfeier der Olympischen Spiele in Rio ein. Alle drei zeigen sich anschließend begeistert. weiterlesen Anzeige Equipment Neuigkeiten aus der Golfwerkstatt weitere Equipment-News > Equipment McIlroy: Wo geht die Reise hin? 08.08.2016 - Rory McIlroy steht seit der Ankündigung von Nike Golf, aus dem Hardware-Markt auszusteigen, fürs kommende Jahr ohne Schlägerausstatter da. Wo geht die Reise hin? weiterlesen Anzeige Training Unsere Tipps verbessern Ihr Spiel. Garantiert! weitere Trainings-Tipps > Mental Training fürs Gehirn 12.08.2016 - Die Gehirnaktivitäten lassen sich auch beim Sport messen. Justin Walsh zeigt, wie es im Gehirn aussieht, wenn ein Golfer sehr gut spielt - und wie, wenn es nicht läuft.  weiterlesen Anzeige DGV-Sport News vom deutschen Spitzensport mehr Sportnews > Aktuelle DGV-Meldungen Qualifikation zur Deutschen Meistschaft Qualifikation DM AK offen Raphael Geißler holt Silber Golf Team Germany Falko Hanisch siegt auf dem 37. Grün Golf Team Germany Finale! Hanisch stark! Golf Team Germany Falko Hanisch im Viertelfinale Golf Team Germany Zweimal Top 10 Golf Team Germany Golf Team Germany Falko Hanisch siegt auf dem 37. Grün 14.08.2016 - Er hat den Titel! Falko Hanisch sichert sich den ganz großen Triumph und setzt sich im Finale der 90. „The Boys Amateur Championship“ nach famosem Kampf auf dem ersten Extraloch durch, nachdem die regulär angesetzten 36 Loch nicht ausgereicht hatten, um einen Sieger zu ermitteln. weiterlesen KRAMSKI DGL Golf Team Germany Regeln Wir beantworten alle Fragen zu Regeln und Regularien alle Regeländerungen 2016 > Golfregeln R&A bietet Decisions-App für Android 25.05.2016 - Neben der Rules of Golf-App bietet der Royal and Ancient Golf Club (R&A) nun auch die Decisions on the Rules of Golf für das Android-Betriebssystem an. weiterlesen DGV Golfregel-App Golfregeln ab 2016 Entscheidungen zu den Golfregeln 2016... 17.05.2016 - Die Entscheidungen zu den Golfregeln sind die offizielle Interpretation der Golfregeln mit Antworten zu nahezu allen strittigen Regelfällen beim Golfspiel. weiterlesen Anzeige DGV-Services Wichtige Hinweise vom Deutschen Golf Verband weitere DGV-Nachrichten > DGV-Services Downloads (Infoservice) mehr Landkarte Golfmarkt 2014 mehr Statistiken Deutsches Golf wächst weiter mehr DGV-Presse mehr Anzeige Impressum  Mediadaten  AGB  dgo-Presse  DGV-Presse  DGV-Datenschutz  Kontakt  Newsletter  DGV - Über uns  dgo - Über uns © 2016 deutsche golf online GmbH Nutzungsbasierte Online-Werbung Tournews Tournews Spielersuche European Tour PGA Tour Ladies European Tour LPGA Champions Tour Challenge Tour Pro Golf Tour Specials Masters Tournament US Open British Open PGA Championship Olympische Spiele Ryder Cup Solheim Cup Porsche European Open BMW International Open Winstongolf Senior Open Ranking Tourkalender Leaderboard Olympia 2016 Leaderboard PGA Tour Leaderboard Web.com Tour DGV-Sport DGV-Sport Kramski Deutsche Golf Liga Golf Team Germany #Vorfreude DGV-Wettspiele Lochspielmeisterschaft DM AK 50 / 65 DM AK 35 DMM AK 50 DM Golfer mit Behinderung Internationale Wettspiele German Girls Open German Boys Open IAM AK 50 Team-EMs IAM Herren IAM Damen DGV-Wettspielanmeldung Ranglisten Kinder & Jugend Jungsenioren Senioren Golfer mit Behinderungen Anti-Doping Golfeinstieg Golfeinstieg Erste Schritte Schnuppern Golferlebnis für 19 Euro faszinationgolf.de Platzreife Golf-ABC Interessenten-Datenbank Regeln Regeln Golfregeln Vorgabensystem Änderungen ab 2016 Amateurstatut Etikette Spielformen Handicap Regelquiz Regeln für Kids Training Training Am Abschlag Annäherungen Rettungsschläge Kurzes Spiel Putten Grundlagen Mental Strategie & Taktik Fitness & Ernährung Teaching Pros PGA of Germany News DGV-Trainer Unsere Trainer Equipment Equipment Nachrichten Expertentipps In the Bag Demodays Test und Kaufberatung Plätze Plätze Deutschland International Clubnachrichten Greenfee-Nachrichten Reisen Reisen Reiseberichte Angebote Hotels Panorama Panorama Panorama News Blogs Tobis #PerfectDrive Ein Münchner auf Reisen Max vor Ort Handicap Papa Mos Roadblog BenZinger Stilfrage Golfevents Ergebnisse & Statistiken Zocks & Spielformen Hintergrundbild DGV-Services DGV-Services DGV Aufgaben / Ziele Golfentwicklung Vision Gold Mitglieder Gremien Geschäftsstelle Verbandsordnung Vermarktung (DGS) Ausbildung Aktuelle Termine Trainerausbildung Golf-Physio-Coach Golfmanagement Seminarplaner Betrieb Golfanlagen Greenkeeping DGV-Ausweis IT-Services Betriebswirtschaft Beratung Versicherungen Recht & Steuern Marketing Golfmarkt Statistiken Marktforschung Landkarten Jugendarbeit QM Nachwuchsförderung Lucky 33 Grünes Band Umwelt Golf&Natur Biodiversität Bewässerung Pflanzenschutz Downloads (Infoservice) Publikationen Presse DGV-Partnershop DGV-Gewinnspiel Golf.de Services Stellenmarkt Newsletter Wetter Hintergrundbild Golf.de Konto Golfclubs Produkte für Golfclubs Gewinnspiel-Sieger Turnierkalender Alle Turniere Turnieranmeldungen Turnierergebnisse Handicap-Abfrage Spielvorgabenrechner Hole in One Club mygolf Login Golf.de Shop Bilder Videos";
		ArrayList<Person> alp = pex.getPersons(names, textline);
		//System.out.println(alp.size());
		assertTrue(16 == alp.size());
		//fail("Not yet implemented");
	}
	
	@Test
	public void testBeautify() {
		PersonExtractor pex = new PersonExtractor();
		String textline = "HolgerReinecke/Peter Müller";
		String newtextline = pex.beautifyLine(textline);
		assertTrue("Holger Reinecke Peter Müller".equalsIgnoreCase( newtextline));
	}
	
	@Test
	public void testBeautify_NumberinText() {
		PersonExtractor pex = new PersonExtractor();
		String textline = "HolgerReinecke1/Peter Müller";
		String newtextline = pex.beautifyLine(textline);
		assertTrue("Holger Reinecke 1 Peter Müller".equalsIgnoreCase( newtextline));
	}

}
