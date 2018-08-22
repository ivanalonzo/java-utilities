package co.alonsos.java_utilities.lang;

import co.alonsos.java_utilities.Constants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * This is a POJO for sending content to the TextContent API as a JSON object
 * @author ivanalonso
 *
 */
public class Content {

	String title;
	String content;
	String[] sentences;
	boolean hasSentences = false;
	int wordCount;

	public boolean isHasSentences() {
		if (sentences != null && sentences.length > 0) {
			hasSentences = true;
		}
		return hasSentences;
	}
	
	public void setSentences(String[] sents) throws Exception {
		if (content == null) {
			sentences = sents;
		}else {
			throw new Exception (Constants.CONTENT_SET);
		}
	}
	
	public void setContent(String cont) throws Exception {
		if (sentences == null) {
			content = cont;
		}else {
			throw new Exception (Constants.SENTENCES_SET);
		}
	}

}
