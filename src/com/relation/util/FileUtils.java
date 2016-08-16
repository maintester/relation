package com.relation.util;

import java.util.Date;

public class FileUtils {

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
		return "resultfiles/";
	}
}
