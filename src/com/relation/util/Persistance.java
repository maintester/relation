package com.relation.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * Read and Write Objects
 * @author casio1
 *http://beginwithjava.blogspot.de/2011/04/java-file-save-and-file-load-objects.html
 */
public class Persistance {

	private String saveDir = "saveobjects/"; 
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
			ret =  save.readObject();
			// Close the file.
			save.close(); // This also closes saveFile.
		} catch (Exception exc) {
			exc.printStackTrace(); // If there was an error, print the info.
		}
		return ret;

	}
}
