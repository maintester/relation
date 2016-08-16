package com.relation.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.relation.models.Person;
import com.relation.models.Relation;

/**
 * Read and Write Objects
 * 
 * @author casio1
 *         http://beginwithjava.blogspot.de/2011/04/java-file-save-and-file
 *         -load-objects.html
 */
public class Persistance {

	private String saveDir = "saveobjects/";

	// ************************************************************************
	public Set<String> readNameFile(String filename) {
		Set<String> values = new HashSet<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(FileUtils.getPathNameFiles() + filename));
			for (String line; (line = br.readLine()) != null;) {
				// System.out.println(line);
				values.add(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return values;
	}

	// ************************************************************************
	public boolean writeResultFile(String filename, Set<String> values) {
		for (String s : values) {
			try {
				Files.write(Paths.get(FileUtils.getPathResultFiles() + filename), s.getBytes(), StandardOpenOption.CREATE,
						StandardOpenOption.APPEND);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}

		}
		return true;
	}

	// ************************************************************************
	public Set<String> readTextFile(String filename) {
		Set<String> values = new HashSet<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(FileUtils.getPathTextFiles() + filename));
			for (String line; (line = br.readLine()) != null;) {
				// System.out.println(line);
				values.add(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return values;
	}

	// ************************************************************************
	public Set<String> readResultFile(String filename) {
		Set<String> values = new HashSet<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(FileUtils.getPathResultFiles() + filename));
			for (String line; (line = br.readLine()) != null;) {
				// System.out.println(line);
				values.add(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return values;
	}

	// ********************************************************++**************
	public String read(String filename) {
		try {
			return new String(Files.readAllBytes(Paths.get(filename)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public String insertPerson(Person o) {
		String sql = "insert into person(personid, lastname, midname, firstname ) values ( 'idx', 'X1' , 'X2', 'X3' )";
		sql = sql.replace("X1", o.lastName);
		sql = sql.replace("X2", o.midName);
		sql = sql.replace("X3", o.firstName);
		String complete = o.lastName + o.midName + o.firstName;
		String idx = this.generateMD5(complete);
		sql = sql.replace("idx", "" + idx);
		boolean bSuccess = doStatement(sql);
		if (!bSuccess) {
			return "0";
		}
		return idx;
	}

	public String insertRelation(Relation o) {
		// String sql =
		// "insert into relation(relationid, personid1, personid2, strenght) values ( 'idx', 'X1' , 'X2', X3 )";
		// sql = sql.replace("X1", o.personId1);
		// sql = sql.replace("X2", o.personId2);
		// sql = sql.replace("X3", ""+o.strenght);
		//
		// String complete = o.personId1 + o.personId2 ;
		// String idx = this.generateMD5(complete);
		// sql = sql.replace("idx", "" + idx);
		//
		// boolean bSuccess = doStatement(sql);
		// if (!bSuccess) {
		// return "0";
		// }
		// return idx;
		return "";
	}

	public boolean doStatement(String statement) {
		System.out.println(statement);
		try {
			// create a mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost/relation1";
			Class.forName(myDriver);
			Connection conn = (Connection) DriverManager.getConnection(myUrl, "user1", "user1");

			Statement st = (Statement) conn.createStatement();
			int ret = st.executeUpdate(statement);
			System.out.println("ret " + ret);

			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

	public void saveObject(Object o) {
		try { // Catch errors in I/O if necessary.
				// Open a file to write to, named SavedObj.sav.
			FileOutputStream saveFile = new FileOutputStream(saveDir + o.getClass().getSimpleName() + ".sav");

			// Create an ObjectOutputStream to put objects into save file.
			ObjectOutputStream save = new ObjectOutputStream(saveFile);

			// Now we do the save.
			save.writeObject(o);
			// Close the file.
			save.close(); // This also closes saveFile.
		} catch (Exception exc) {
			exc.printStackTrace(); // If there was an error, print the info.
		}
	}

	public Object loadObject(String objClass) {
		Object ret = null;
		try {
			// Open file to read from, named SavedObj.sav.
			FileInputStream saveFile = new FileInputStream(saveDir + objClass + ".sav");
			// Create an ObjectInputStream to get objects from save file.
			ObjectInputStream save = new ObjectInputStream(saveFile);
			// Now we do the restore.
			// readObject() returns a generic Object, we cast those back
			// into their original class type.
			// For primitive types, use the corresponding reference class.
			ret = save.readObject();
			// Close the file.
			save.close(); // This also closes saveFile.
		} catch (Exception exc) {
			exc.printStackTrace(); // If there was an error, print the info.
		}
		return ret;

	}

	public static String generateMD5(String message) {
		try {
			return hashString(message, "MD5");
		} catch (Exception e) {
			return "" + new Date().getTime();
		}
	}

	public static String generateSHA1(String message) throws HashGenerationException {
		return hashString(message, "SHA-1");
	}

	public static String generateSHA256(String message) throws HashGenerationException {
		return hashString(message, "SHA-256");
	}

	private static String hashString(String message, String algorithm) throws HashGenerationException {

		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

			return convertByteArrayToHexString(hashedBytes);
		} catch (UnsupportedEncodingException ex) {
			throw new HashGenerationException("Could not generate hash from String", ex);
		} catch (NoSuchAlgorithmException ex) {
			throw new HashGenerationException("Could not generate hash from String", ex);
		}

	}

	private static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}

}
