package co.alonsos.java_utilities;

import java.util.HashMap;

public class Constants {
	
	public static String CONTENT_SET = "There is content already set, you can only set content or sentences";
	public static String SENTENCES_SET = "There are sentences arleady set, you can only set content or sentences";

	/**
	 * A static method which returns a map of conversion strings
	 * for use by cleanup methods
	 * @return
	 */
	public static final HashMap<String, String> cleanupMap(){
		HashMap<String,String> map =new HashMap<String,String>();  
		//Look for hex encoded chars and replace them 
		map.put("&#x2014;", " - ");
		map.put("&#x201D;", "\"");
		map.put("&#x201C;", "\"");
		map.put("&#x2018;", "'");
		map.put("&#x2019;", "'");
		
		//Look for HTML encoded chars and replace them
		map.put("&quot;", "\"");
		map.put("&apos;", "'");
		map.put("&amp;", "&");
		return map;
		
	}
	
	/**
	 * A static method which returns a map of ASCII to HTML tags for use 
	 * by a conversion method
	 * @return
	 */
	public static final HashMap<String, String> toHTMLMap(){
		HashMap<String,String> map =new HashMap<String,String>();  
		map.put("\\n", "<br>");
		map.put("\\s\\s", "<br>");
		return map;
		
	}
}
