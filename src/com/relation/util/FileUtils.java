package com.relation.util;

import java.util.Date;

public class FileUtils {
	private static String OS = System.getProperty("os.name").toLowerCase();
	public static String getTStamp (){
		return ""+new Date().getTime();
	}
	public static String getItemDelim (){
		return "|";
	}
	public static String getLineDelim (){
		return "\r\n";
	}
	public static String getPathTextFiles(){
		return "textfiles/";
	}
	public static String getPathNameFiles(){
		return "namefiles/";
	}
	public static String getPathResultFiles(){
		if(OS.equalsIgnoreCase("windows 7")){
			return "K:/relation2u/resultfiles/";
		}
		return "resultfiles/";
	}
}
