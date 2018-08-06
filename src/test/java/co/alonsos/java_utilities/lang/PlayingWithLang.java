package co.alonsos.java_utilities.lang;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

public class PlayingWithLang {
	@Test
	public void test() throws Exception {
		JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
		String paragraph = "Sen. Bernie Sanders' \"Medicare for all\" plan would increase government healthcare spending by $32.6 trillion over 10 years, according to a study by a university-based libertarian policy center.\n"
		        + "\n"
		        + "Sanders called the report \"grossly misleading and biased\" in a statement, but his office has not conducted its own cost analysis.\n"
		        + "\n"
		        + "WASHINGTON — Sen. Bernie Sanders' \"Medicare for all\" plan would increase government healthcare spending by $32.6 trillion over 10 years, according to a study by a university-based libertarian policy center.\n"
		        + "\n"
		        + "The latest plan from the Vermont independent would require historic tax increases as government replaces what employers and consumers now pay for healthcare, according to the analysis being released Monday by the Mercatus Center at George Mason University in Virginia.\n"
		        + "\n" + "Sanders' plan builds on Medicare, the popular insurance program for seniors.\n" + "\n"
		        + "Responding to the study, Sanders took aim at the Mercatus Center, which receives funding from the conservative Koch brothers.\n"
		        + "\n"
		        + "However, the Mercatus estimates are within the range of other cost projections for Sanders' 2016 plan.\n"
		        + "\n"
		        + "The Mercatus study takes issue with a key cost-saving feature of the plan: that hospitals and doctors would accept payment based on lower Medicare rates for all their patients.\n"
		        + "\n"
		        + "The study found that the plan would reap substantial savings from lower prescription costs — $846 billion over 10 years — since the government would deal directly with drugmakers.\n"
		        + "\n"
		        + "After taking into account current government healthcare financing, the study estimated that doubling all federal individual and corporate income taxes would not fully cover the additional costs.";

//		List<RuleMatch> matches = langTool.check(paragraph);
//		for(RuleMatch match : matches) {
//			System.out.println("Potential error at characters " + match.getFromPos() + "-" + match.getToPos() + ": "
//			        + match.getMessage());
//			System.out.println("Suggested correction(s): " + match.getSuggestedReplacements());
//			System.out.println("Column: " + match.getColumn());
//		}
		String finalSentence = LangTools.fixSpaces(paragraph);

//		// System.out.println(finalSentence.trim());
//		System.out.println("-------------------------------");
//		matches = langTool.check(finalSentence.trim());
//		for(RuleMatch match : matches) {
//			System.out.println("Potential error at characters " + match.getFromPos() + "-" + match.getToPos() + ": "
//			        + match.getMessage());
//			System.out.println("Suggested correction(s): " + match.getSuggestedReplacements());
//			System.out.println("Column: " + match.getColumn());
//		}
	}

}
