//package co.alonsos.java_utilities.lang;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import org.apache.log4j.Logger;
//import org.junit.jupiter.api.Test;
//import org.languagetool.JLanguageTool;
//import org.languagetool.language.AmericanEnglish;
//import org.languagetool.rules.RuleMatch;
//import com.google.gson.Gson;
//import co.alonsos.java_utilities.io.IO_Utils;
////import edu.stanford.nlp.coref.data.CorefChain;
////import edu.stanford.nlp.ling.*;
////import edu.stanford.nlp.ie.util.*;
////import edu.stanford.nlp.pipeline.*;
////import edu.stanford.nlp.semgraph.*;
////import edu.stanford.nlp.trees.*;
//import edu.stanford.nlp.simple.*;
//import java.util.*;
//import opennlp.tools.sentdetect.SentenceDetectorME;
//import opennlp.tools.sentdetect.SentenceModel;
//import opennlp.tools.util.Span;
//
//public class PlayingWithLang {
//	private static Logger log = Logger.getLogger(PlayingWithLang.class);
//	Gson gson = new Gson();
//
//	@Test
//	public void test() throws Exception {
//		JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
//		String paragraph = "Offer a production electric street motorcycle in the U.S.Competitive pressure from EV upstarts—added to Harley’s EV production commitments.";
//
//		List<RuleMatch> matches = langTool.check(paragraph);
//		for(RuleMatch match : matches) {
//			System.out.println("Potential error at characters " + match.getFromPos() + "-" + match.getToPos() + ": "
//			        + match.getMessage());
//			System.out.println("Suggested correction(s): " + match.getSuggestedReplacements());
//			System.out.println("Column: " + match.getColumn());
//		}
//		// String finalSentence = LangTools.fixSpaces(paragraph);
//
////		// System.out.println(finalSentence.trim());
////		System.out.println("-------------------------------");
////		matches = langTool.check(finalSentence.trim());
////		for(RuleMatch match : matches) {
////			System.out.println("Potential error at characters " + match.getFromPos() + "-" + match.getToPos() + ": "
////			        + match.getMessage());
////			System.out.println("Suggested correction(s): " + match.getSuggestedReplacements());
////			System.out.println("Column: " + match.getColumn());
////		}
//	}
//
//	String[] inputSentences = {
//	        "Washington (CNN) The Ohio special election for a House seat that's been in Republican hands for three decades was too close to call Tuesday night.",
//	        "With early and election day votes counted, Republican Troy Balderson has a 0.9-percentage-point edge over Democrat Danny O'Connor, with absentee and provisional ballots remaining.",
//	        "The other closely watched race where Trump was involved, the Kansas GOP gubernatorial primary, continued into the early Wednesday morning hours.",
//	        "Here are four early takeaways from Tuesday's Ohio special election and primaries in Kansas, Michigan, Missouri and Washington.",
//	        "It's time for Republicans to panic about the HouseThe neck-and-neck race in a ruby red Ohio district was the latest evidence that the Republican majority in the House is in peril.",
//	        "There are 68 Republican-held House districts that are more favorable for Democrats, per the Cook Political Report's \"Partisan Voter Index,\" which factors in recent election results -- and Democrats only need a net gain of 23 seats to take control.",
//	        "The bottom line: Democratic voters are energized, the suburbs are swinging in their favor and Republicans are sitting elections out.",
//	        "Late into the night, Republicans were watching as yet another reason to worry emerged: In Washington state, the leading Republicans in three GOP-held districts were all being held under 50% of the vote in the state's jungle primary, where Democrats and Republicans compete in the same contest and the top two finishers advance.",
//	        "Their move: Put the referendum on the ballot in the August primary, when they expected strong Republican turnout, rather than in the November general election, when they expected pro-union Democrats would show up in full force.",
//	        "Missouri voters rejected the right-to-work law by a 2-to-1 margin -- a remarkable defeat for a Republican priority in a state Trump won by 19 percentage points in 2016.",
//	        "In the night's marquee contest, progressive outsider Abdul El-Sayed fell well short in his bid to upset former state Senate Minority leader Gretchen Whitmer, who won the nomination at a canter and is now poised for a November showdown with Republican Attorney General Bill Schuette.",
//	        "Keeping with the mixed-bag theme of the night, former state Rep. Rashida Tlaib is leading in the race to replace former Rep. John Conyers Jr., while Fayrouz Saad lagged far behind in her contest.",
//	        "And it's shaping up to be a split decision Kansas, where labor lawyer Brent Welder is locked in a tight race with Sharice Davids in the state's Third District.",
//	        "In July, Sanders and Ocasio-Cortez rallied for Welder and James Thompson, who won the Fourth District primary Tuesday and secured a rematch of his 2017 special election against GOP Rep. Ron Estes."
//	};
//
//	String[] inputSentences2 = {
//	        "Sen. Bernie Sanders' \"Medicare for all\" plan would increase government healthcare spending by $32.6 trillion over 10 years, according to a study by a university-based libertarian policy center.",
//	        "Sanders called the report \"grossly misleading and biased\" in a statement, but his office has not conducted its own cost analysis.",
//	        "WASHINGTON — Sen. Bernie Sanders' \"Medicare for all\" plan would increase government healthcare spending by $32.6 trillion over 10 years, according to a study by a university-based libertarian policy center.",
//	        "The latest plan from the Vermont independent would require historic tax increases as government replaces what employers and consumers now pay for healthcare, according to the analysis being released Monday by the Mercatus Center at George Mason University in Virginia.",
//	        "Sanders' plan builds on Medicare, the popular insurance program for seniors.",
//	        "Responding to the study, Sanders took aim at the Mercatus Center, which receives funding from the conservative Koch brothers.",
//	        "However, the Mercatus estimates are within the range of other cost projections for Sanders' 2016 plan.",
//	        "The Mercatus study takes issue with a key cost-saving feature of the plan: that hospitals and doctors would accept payment based on lower Medicare rates for all their patients.",
//	        "The study found that the plan would reap substantial savings from lower prescription costs — $846 billion over 10 years — since the government would deal directly with drugmakers.",
//	        "However, the Mercatus estimates are within the range of other costs projections for Sanders' 2015 plan.",
//	        "After taking into account current government healthcare financing, the study estimated that doubling all federal individual and corporate income taxes would not fully cover the additional costs."
//	};
//
//	String[] inputSentences3 = {
//	        "None of the big names—Honda, Kawasaki, Suzuki, BMW, KTM—offer a production electric street motorcycle in the U.S.Competitive pressure from EV upstarts—added to Harley’s EV production commitments—could pressure the likes of Honda, Yamaha, and Ducati to produce electric motorcycles sooner."
//	};
//
//	String[] inputSentences4 = {
//	        "Photo: Contributed Photo / Department Of Justice / Contributed Photo Jeffrey Pearlman, 51, of Edgewood, New Jersey, pleaded guilty to one count of conspiracy to violate the anti-kickback law.",
//	        "less Jeffrey Pearlman, 51, of Edgewood, New Jersey, pleaded guilty to one count of conspiracy to violate the anti-kickback law.",
//	        "The offense carries a maximum term of five years in prison and a fine of up to ... more Photo: Contributed Photo / Department Of Justice / Contributed Photo Feds: Drug company manager pleads guilty in kickback scheme Nearly two years after his arrest, a drug company manager admitted his role in a kickback scheme related to fentanyl spray prescriptions in federal court on Wednesday, the Department of Justice said.",
//	        "The DOJ said Pearlman’s involvement in the scheme defrauded federal health care programs.",
//	        "He would eventually be promoted to district sales manager, a position which left him responsible for managing the company’s sales representatives who called on licensed health care provides in Connecticut, New York, New Jersey and Rhode Island.",
//	        "The DOJ said Pearlman attended a dinner at a New Haven restaurant in 2013 where a Connecticut health care provider was paid a speaker fee even though no other health care professional were there and no presentation on Subsys was given.",
//	        "\"In June 2013, when these prescriptions were not initially being written as planned, Pearlman emailed the Insys sales representative who was responsible for calling on the provider and reiterated that per the ‘verbal agreement’ Pearlman had made with the provider, the provider needed to write more Subsys prescriptions or he was ‘going to have tremendous difficulty in justifying more (speaker) programs’,\" the DOJ said."
//	};
//
//	String inputSentence5 = "The offense carries a maximum term of five years in prison and a fine of up to ... more Photo: Contributed Photo / Department Of Justice / Contributed Photo Feds: Drug company manager pleads guilty in kickback scheme Nearly two years after his arrest, a drug company manager admitted his role in a kickback scheme related to fentanyl spray prescriptions in federal court on Wednesday, the Department of Justice said.";
//
//	String text = "Joe Smith was born in California. " + "In 2017, he went to Paris, France in the summer. "
//	        + "His flight left at 3:00pm on July 10th, 2017. "
//	        + "After eating some escargot for the first time, Joe said, \"That was delicious!\" "
//	        + "He sent a postcard to his sister Jane Smith. "
//	        + "After hearing about Joe's trip, Jane decided she might go to France one day.";
//
//	String text2 = "Photo: Contributed Photo / Department Of Justice / Contributed Photo Jeffrey Pearlman, 51, of Edgewood, New Jersey, pleaded guilty to one count of conspiracy to violate the anti-kickback law.";
//	@Test
//	public void test2() {
//		double dupThreshold = 45;
//		IO_Utils io = new IO_Utils();
//		String pathToInput = "/Users/ivanalonso/Desktop/Content_test_files/duplicate-text-k462618-wp126552.txt";
//		String title = "Title of Content";
//		try {
//			TextContent content = new TextContent(title, inputSentences4);
//			// log.debug(gson.toJson(content));
//			log.debug(content.getTitle());
//			log.debug(content.getContentParagraphs().size());
//
////			for(ContentParagraph para : content.getContentParagraphs()) {
////				log.debug(para.getRawParagraph());
////				log.debug(para.getSimRat());
////				log.debug(para.getErMsg());
////
////			}
//
//			content.rateDuplicates(dupThreshold);
//			log.debug("=================");
//			for(ContentParagraph para : content.getContentParagraphs()) {
//				log.debug(para.getSentenceCount());
//				log.debug(para.getParagraph());
//				log.debug(para.getSimRat());
//				log.debug(para.getParagraphGramSuggestions());
//				log.debug(para.getErMsg());
//				log.debug("++++++++++++++++");
//			}
//
//			log.debug(content.getCleanContent(dupThreshold));
//
////			for(ContentParagraph paragraph : content.getContentParagraphs()) {
////				log.debug(paragraph.getRawParagraph());
////			}
////			content.rateDuplicates();
////			log.debug("=================");
////			for(ContentSentence sentence : content.getContentSentences()) {
////				log.debug(sentence.getSentence());
////				log.debug(sentence.getSimRat());
////				log.debug(sentence.getErMsg());
////			}
////			log.debug(content.christina());
//		}catch (Throwable e) {
//			log.error(e.getMessage(), e);
//		}
//
//
//	}
//
//	@Test
//	public void test3() {
//		String modelPath = "src/main/resources/OpenNLPModels/en-sent.bin";
//		try (InputStream modelIn = new FileInputStream(modelPath)) {
//			SentenceModel model = new SentenceModel(modelIn);
//			SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
//
//			String[] sentences = sentenceDetector.sentDetect(inputSentence5);
//			for(int i = 0; i < sentences.length; i++) {
//				log.debug(sentences[i]);
//			}
//		}catch (Exception e) {
//
//		}
//	}
//
////	@Test
////	public void test4() {
////		// set up pipeline properties
////		Properties props = new Properties();
////		// set the list of annotators to run
////		props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,coref,kbp,quote");
////		// set a property for an annotator, in this case the coref annotator is being set to use the neural
////		// algorithm
////		props.setProperty("coref.algorithm", "neural");
////		// build pipeline
////		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
////		// create a document object
////		CoreDocument document = new CoreDocument(text);
////		// annnotate the document
////		pipeline.annotate(document);
////		// examples
////
////		// 10th token of the document
////		CoreLabel token = document.tokens().get(10);
////		System.out.println("Example: token");
////		System.out.println(token);
////		System.out.println();
////
////		// text of the first sentence
////		String sentenceText = document.sentences().get(0).text();
////		System.out.println("Example: sentence");
////		System.out.println(sentenceText);
////		System.out.println();
////
////		// second sentence
////		CoreSentence sentence = document.sentences().get(1);
////
////		// list of the part-of-speech tags for the second sentence
////		List<String> posTags = sentence.posTags();
////		System.out.println("Example: pos tags");
////		System.out.println(posTags);
////		System.out.println();
////
////		// list of the ner tags for the second sentence
////		List<String> nerTags = sentence.nerTags();
////		System.out.println("Example: ner tags");
////		System.out.println(nerTags);
////		System.out.println();
////
////		// constituency parse for the second sentence
////		Tree constituencyParse = sentence.constituencyParse();
////		System.out.println("Example: constituency parse");
////		System.out.println(constituencyParse);
////		System.out.println();
////
////		// dependency parse for the second sentence
////		SemanticGraph dependencyParse = sentence.dependencyParse();
////		System.out.println("Example: dependency parse");
////		System.out.println(dependencyParse);
////		System.out.println();
////
////		// kbp relations found in fifth sentence
////		List<RelationTriple> relations = document.sentences().get(4).relations();
////		System.out.println("Example: relation");
////		System.out.println(relations.get(0));
////		System.out.println();
////
////		// entity mentions in the second sentence
////		List<CoreEntityMention> entityMentions = sentence.entityMentions();
////		System.out.println("Example: entity mentions");
////		System.out.println(entityMentions);
////		System.out.println();
////
////		// coreference between entity mentions
////		CoreEntityMention originalEntityMention = document.sentences().get(3).entityMentions().get(1);
////		System.out.println("Example: original entity mention");
////		System.out.println(originalEntityMention);
////		System.out.println("Example: canonical entity mention");
////		System.out.println(originalEntityMention.canonicalEntityMention().get());
////		System.out.println();
////
////		// get document wide coref info
////		Map<Integer, CorefChain> corefChains = document.corefChains();
////		System.out.println("Example: coref chains for document");
////		System.out.println(corefChains);
////		System.out.println();
////
////		// get quotes in document
////		List<CoreQuote> quotes = document.quotes();
////		CoreQuote quote = quotes.get(0);
////		System.out.println("Example: quote");
////		System.out.println(quote);
////		System.out.println();
////
////		// original speaker of quote
////		// note that quote.speaker() returns an Optional
////		System.out.println("Example: original speaker of quote");
////		System.out.println(quote.speaker().get());
////		System.out.println();
////
////		// canonical speaker of quote
////		System.out.println("Example: canonical speaker of quote");
////		System.out.println(quote.canonicalSpeaker().get());
////		System.out.println();
////
////	}
//
//	String text3 = "Photo: Contributed Photo / Department Of Justice / Contributed Photo Jeffrey Pearlman, 51, of Edgewood, New Jersey, pleaded guilty to one count of conspiracy to violate the anti-kickback law.\n"
//	        + "\n"
//	        + "The offense carries a maximum term of five years in prison and a fine of up to ... more Photo: Contributed Photo / Department Of Justice / Contributed Photo Feds: Drug company manager pleads guilty in kickback scheme Nearly two years after his arrest, a drug company manager admitted his role in a kickback scheme related to fentanyl spray prescriptions in federal court on Wednesday, the Department of Justice said.\n"
//	        + "\n" + "The DOJ said Pearlman’s involvement in the scheme defrauded federal health care programs.\n"
//	        + "\n"
//	        + "He would eventually be promoted to district sales manager, a position which left him responsible for managing the company’s sales representatives who called on licensed health care provides in Connecticut, New York, New Jersey and Rhode Island.\n"
//	        + "\n"
//	        + "The DOJ said Pearlman attended a dinner at a New Haven restaurant in 2013 where a Connecticut health care provider was paid a speaker fee even though no other health care professional were there and no presentation on Subsys was given.\n"
//	        + "\n"
//	        + "\"In June 2013, when these prescriptions were not initially being written as planned, Pearlman emailed the Insys sales representative who was responsible for calling on the provider and reiterated that per the ‘verbal agreement’ Pearlman had made with the provider, the provider needed to write more Subsys prescriptions or he was ‘going to have tremendous difficulty in justifying more (speaker) programs’,\" the DOJ said.";
//
//	@Test
//	public void test5() {
//		// Create a document. No computation is done yet.
//		Document doc = new Document(text3);
//		for(Sentence sent : doc.sentences()) {  // Will iterate over two sentences
////			// We're only asking for words -- no need to load any models yet
////			log.debug("The second word of the sentence '" + sent + "' is " + sent.word(1));
////			// When we ask for the lemma, it will load and run the part of speech tagger
////			log.debug("The third lemma of the sentence '" + sent + "' is " + sent.lemma(2));
////			// When we ask for the parse, it will load and run the parser
////			log.debug("The parse of the sentence '" + sent + "' is " + sent.parse());
////			// ...
//			log.debug(sent.parse().score() + "====" + sent.text());
//			log.debug(gson.toJson(sent.natlogPolarities()) + "====" + sent.text());
//
//		}
//	}
//}
