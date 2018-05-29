package co.alonsos.java_utilities.io;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import co.alonsos.java_utilities.Constants;


public class IO_Text {
	private static Logger log = Logger.getLogger(IO_Text.class);

	/**
	 * Given the input, it will replace strings with their HTML counterparts. It currently has the
	 * following support: 
	 * \n --> <br><br>
	 * \s\s --> <br>
	 * @param input
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String convertToHTML(String input) throws Exception {
		String converted = "";
		HashMap<String, String> convertMap = Constants.toHTMLMap();
		for (Map.Entry m : convertMap.entrySet()) {
			input = input.replaceAll(m.getKey().toString(), m.getValue().toString());
		}
		converted = input;
		return converted;
	}
	
	/**
	 * Given input string it will remove HTML tags. If cleanup is null, it will use
	 * the static map in Constants. 
	 * @param input String to be cleaned up
	 * @param cleanup not required. 
	 * @return clean String which is also trimmed 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String cleanHTMLTagsAndEncoding(String input, HashMap<String, String> cleanup) throws Exception{
		String clean = "";
		
		HashMap<String, String> convertMap = null;
		if(cleanup == null) {
			convertMap = Constants.cleanupMap();
		}else {
			convertMap = cleanup;
		}
		
		//Look for HTML tags and remove them 
		input = input.replaceAll("(\\<)(.*?)(\\>)", " ");

		for (Map.Entry m : convertMap.entrySet()) {
			input = input.replaceAll(m.getKey().toString(), m.getValue().toString());
		}
		clean = input;
		return clean.trim();
	}

	public String cleanText(String input, boolean removeNewLines) {
		String clean = "";
		String[] words;
		clean = input.trim();
		/*
		 * We have to do multiple clean up on the tabs and next lines because if the input already
		 * includes the \t in the text, repalceAll will not work the same way as it does for embedded
		 * tabs.
		 */
		clean = clean.replaceAll("\\\\t", "");
		clean = clean.replaceAll("\\t", "");

		if (removeNewLines) {
			clean = clean.replaceAll("\\\\n", "");
			clean = clean.replaceAll("\\n", "");
		}else {
			clean = clean.replaceAll("\\\\n", "</p>");
			clean = clean.replaceAll("\\n", "</p>");
		}

		clean = clean.replaceAll("\\|", "");
		words = clean.split("\\s");
		clean = "";
		for(int i = 0; i < words.length; i++) {
			if (!words[i].trim().isEmpty()) {
				clean += words[i].trim() + " ";
			}
		}
		return clean.trim();
	}

	/**
	 * Simple method which uses StrigTokenizer to count the number of words in the given input file
	 * 
	 * @param inputTextOrFilePath you can pass either a path to a file or a string itself
	 * @return
	 * @throws Exception
	 */
	public int wordCount (String inputTextOrFilePath)throws Exception{
		int count = 0;
		if (inputTextOrFilePath != null) {
			IO_Utils io = new IO_Utils();
			if (io.fileExists(inputTextOrFilePath)) {
				log.debug("Will load the file");
				count = new StringTokenizer(io.fileToString(inputTextOrFilePath)).countTokens();
			}else {
				log.debug("Not a file, will use String");
				count = new StringTokenizer(inputTextOrFilePath).countTokens();
			}

			return count;	
		}else {
			return 0;
		}
	}

	public String stringArrayToParagraph(String[] sentences) {
		if (sentences == null) {
			return "";
		}
		return stringArrayToParagraph(sentences, sentences.length);
	}

	/**
	 * Converts an array of strings into a full String with paragraph breaks between each array entry
	 * 
	 * @param sentences array of sentences
	 * @param maxIndex the max number of sentences to be used for building the return object
	 * @return
	 */
	public String stringArrayToParagraph(String[] sentences, int maxIndex) {
		String paragraph = "";
		if (maxIndex < 1) {
			return paragraph;
		}

		if (maxIndex > sentences.length) {
			maxIndex = sentences.length;
		}

		if (sentences != null && sentences.length > 0) {
			for(int i = 0; i < maxIndex; i++) {
				paragraph += sentences[i] + "\n\n";
			}
		}
		return paragraph;
	}

	/**
	 * Allows you to create a paragraph of text on the bottom
	 * 
	 * @param paragraph if null or empty will return empty string
	 * @param appendeture if null or empty will return empty string
	 * @return
	 */
	public String appendParagraph(String paragraph, String appendeture) {
		if (paragraph == null || paragraph.isEmpty()) {
			return "";
		}
		if (appendeture == null || appendeture.isEmpty()) {
			return "";
		}

		return paragraph + appendeture + "\n\n";
	}

	/**
	 * When a String is mis-encoded and you have no control over the encoding, this method will
	 * allow you to find those mis-encoded strings and attempt to fix it by replacing all instances
	 * of the bad encoding.
	 * 
	 * @param input: Badly encoded string
	 * @param encodingMap: It must be a JSON key:value string like this:
	 *        "bad encoding":"replacement char"
	 * @return
	 */
	public String fixEncoding(String input, String encodingMap) {
		String clean = "";
		Gson gson = new Gson();

		HashMap<String, String> decodingMap = gson.fromJson(encodingMap,
		        new TypeToken<HashMap<String, String>>() {}.getType());

		for(Map.Entry m : decodingMap.entrySet()) {
			input = input.replaceAll(m.getKey().toString(), m.getValue().toString());
		}
		clean = input;
		return clean.trim();
	}
}
