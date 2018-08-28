package co.alonsos.java_utilities.lang;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import co.alonsos.java_utilities.io.IO_Text;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentParagraph extends ContentSentence {
	private static Logger log = Logger.getLogger(ContentParagraph.class);

	IO_Text txt = new IO_Text();

	private String rawParagraph;
	private List<ContentSentence> paragraphSentences;
	private int sentenceCount;
	private Double simRat;

	public ContentParagraph(String paragraph) {

		this.rawParagraph = paragraph.trim();
		List<String> sentences = langTool.sentenceTokenize(this.rawParagraph);
		if (sentences.size() > 0) {
			paragraphSentences = new ArrayList<ContentSentence>();
		}
		for(String sentence : sentences) {
			if (sentence != null && !sentence.isEmpty()) {
				paragraphSentences.add(new ContentSentence(sentence.trim()));
				sentenceCount++;
			}
		}
		if (paragraphSentences != null) {
			for(ContentSentence sents : paragraphSentences) {
				wordCount += sents.getWordCount();
			}
		}
		sentences = null;

	}

	public ContentParagraph() {
	}

	/**
	 * Returns ONLY the paragraph Text. It does not include anything else, but the text.
	 * 
	 * @return
	 */
	public String getParagraph() {
		String paragraph = "";
		if (paragraphSentences != null && paragraphSentences.size() > 0) {
			for(ContentSentence sentence : paragraphSentences) {
				paragraph += sentence.getSentence() + " ";
			}
		}

		return paragraph.trim();
	}

	/**
	 * Returns the grammatical suggestions for each paragraph. Since a paragraph can be made up of
	 * multiple sentences, it will concatenate the suggestions for each sentence in the paragraph and
	 * return
	 * the sum of all suggestions.
	 * 
	 * @return
	 */
	public String getParagraphGramSuggestions() {
		String gramSuggestions = "";
		if (paragraphSentences.size() > 0) {
			for(ContentSentence sentence : paragraphSentences) {
				gramSuggestions += sentence.getGramSuggestions() + " ";
			}
		}

		return gramSuggestions.trim();
	}

	public Double getSimRat() {
		if (simRat == null) {
			simRat = -1.0;
		}
		return simRat;
	}

}
