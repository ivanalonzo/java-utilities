package co.alonsos.java_utilities.lang;

import java.util.List;
import org.apache.log4j.Logger;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;

public class LangTools {
	private static Logger log = Logger.getLogger(LangTools.class);

	static JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());

	public static String fixSpaces(String input) throws Exception {
		if (input == null || input.isEmpty()) {
			throw new Exception("Input cannot be null or empty");
		}
		List<String> sentences = langTool.sentenceTokenize(input);
		String finalSentence = "";
		for(String sentence : sentences) {
			log.debug(sentence);
			finalSentence = finalSentence + sentence + " ";
		}

		input = null;
		return finalSentence.trim();
	}

}
