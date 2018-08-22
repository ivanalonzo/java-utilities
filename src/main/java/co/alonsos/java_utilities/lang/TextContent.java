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
public class TextContent extends ContentParagraph{
	private static Logger log = Logger.getLogger(TextContent.class);

	private String newLine = "</p>";

	private String title;
	private String rawContent;
	private List<ContentParagraph> contentParagraphs;

	public TextContent(String title, String rawContent) {
		this.title = title;
		this.rawContent = rawContent;
		String[] paragraphs = rawContent.split(newLine);
		if (paragraphs.length > 0) {
			contentParagraphs = new ArrayList<ContentParagraph>();
			for (int i = 0; i < paragraphs.length; i++) {
				contentParagraphs.add(new ContentParagraph(paragraphs[i]));
			}
		}
		for (ContentParagraph para : contentParagraphs) {
			wordCount += para.getWordCount();
		}
	}
	
	public TextContent(String title, String[] sentences) {
		this.title = title;
		if(sentences.length > 0) {
			contentParagraphs = new ArrayList<ContentParagraph>();
			rawContent = "";
			for (int i = 0; i < sentences.length; i++) {
				contentParagraphs.add(new ContentParagraph(sentences[i]));
				rawContent += sentences[i] + newLine;
			}
		}
		for (ContentParagraph para : contentParagraphs) {
			wordCount += para.getWordCount();
		}
	}
	
	public void rateDuplicates(double dupThreshold) {
		for(int i = 0; i < contentParagraphs.size(); i++) {
			for(int j = i+1; j < contentParagraphs.size(); j++) {
				if (contentParagraphs.get(j).getErMsg() == null) {
					double rating = txt.similarAvgRate(contentParagraphs.get(i).getParagraph(),
					        contentParagraphs.get(j).getParagraph());
					if (rating >= 0.0 && rating < dupThreshold) {
						contentParagraphs.get(j).setSimRat(rating);
						contentParagraphs.get(j).setErMsg("DUPLICATE");;
					}
				}
			}
		}
	}
	
	public String getCleanContent(double dupThreshold) {
		String content = "";
		//De-dup 
		rateDuplicates(dupThreshold);
		for (ContentParagraph para : contentParagraphs) {
			if (para.getErMsg() == null) {
				content += para.getParagraph() + newLine;
			}
		}
		return content.trim(); 
	}
	
	public String[] getCleanSentences(double dupThreshold) {
		List<String> content = new ArrayList<String>();
		//De-dup 
		rateDuplicates(dupThreshold);
		for (ContentParagraph para : contentParagraphs) {
			if (para.getErMsg() == null) {
				content.add(para.getParagraph());
			}
		}
		
		String[] result = new String[content.size()];
		for (int i = 0; i < content.size(); i++) {
			result[i] = content.get(i);
		}
		content = null;
		return result; 
	}
}
