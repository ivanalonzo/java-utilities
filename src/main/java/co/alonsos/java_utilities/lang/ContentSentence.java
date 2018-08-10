package co.alonsos.java_utilities.lang;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentSentence {	
	private static Logger log = Logger.getLogger(ContentSentence.class);

	protected JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());

	private String sentence;
	private String gramSuggestions;
	private String erMsg;
	private int wordCount;
	
	protected ContentSentence() {}
	
	public ContentSentence(String sentence) {
		this.sentence = sentence;
		try {
			wordCount = new StringTokenizer(this.sentence).countTokens();
			checkRules();
		}catch (Exception e) {
			this.erMsg = e.getMessage();
		}
	}
	
	private void checkRules() throws IOException {
		List<RuleMatch> matches = langTool.check(this.sentence);
		this.gramSuggestions = "";
		for(RuleMatch match : matches) {
			this.gramSuggestions += "Potential error at characters " + match.getFromPos() + "-" + match.getToPos() + ": "
			        + match.getMessage() + ". Suggested correction(s): " + match.getSuggestedReplacements() + "\n";
		}
		//log.debug(this.gramSuggestions);
	}
}
