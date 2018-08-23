package co.alonsos.java_utilities.lang;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import co.alonsos.java_utilities.io.IO_Utils;

public class TextContentTest {
	private static Logger log = Logger.getLogger(TextContentTest.class);

	IO_Utils io = new IO_Utils();

	@Test
	public void testTextContent() throws IOException {
		String expected = io.fileToString("src/test/resources/unit_test_files/TextContent/Exp.NormalFullContent.txt");
		double dupThreshold = 30.0;
		String title = "Normal Full Content";
		String rawContent = io.fileToString("src/test/resources/unit_test_files/TextContent/NormalFullContent.txt");
		TextContent cont = new TextContent(title, rawContent);
		String actual = cont.getCleanContent(dupThreshold);
		Assert.assertEquals(3, cont.getContentParagraphs().size());
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testTextContentRateDups() throws IOException {
		double dupThreshold = 30.0;
		String title = "Normal Full Content";
		String rawContent = io.fileToString("src/test/resources/unit_test_files/TextContent/NormalFullContent.txt");
		TextContent cont = new TextContent(title, rawContent);
		cont.rateDuplicates(dupThreshold);
		for(ContentParagraph para : cont.getContentParagraphs()) {
			Assert.assertEquals(-1.0, para.getSimRat(), 0.0);
		}
	}

	@Test
	public void testTextContentRateDups1Dup() throws IOException {
		String expected = io.fileToString("src/test/resources/unit_test_files/TextContent/Exp.NormalFullContent.txt");
		double dupThreshold = 30.0;
		String title = "Normal Full Content";
		String rawContent = io.fileToString("src/test/resources/unit_test_files/TextContent/DuplicateFullCont.txt");
		TextContent cont = new TextContent(title, rawContent);
		cont.rateDuplicates(dupThreshold);
		// We know it's the 3 para that is a duplicate
		Assert.assertEquals(0.0, cont.getContentParagraphs().get(2).getSimRat(), 0.0);
		// We expect that in clean up, this para will be removed.
		String actual = cont.getCleanContent(dupThreshold);
		// We expect 4, because while we don't include the duplicate para in the cleanup, it is still part
		// of the
		// object
		Assert.assertEquals(4, cont.getContentParagraphs().size());
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testTextContentRateDups1Sim() throws IOException {
		String expected = io.fileToString("src/test/resources/unit_test_files/TextContent/Exp.NormalFullContent.txt");
		double dupThreshold = 30.0;
		String title = "Normal Full Content";
		String rawContent = io.fileToString("src/test/resources/unit_test_files/TextContent/SimilarFullCont.txt");
		TextContent cont = new TextContent(title, rawContent);
		cont.rateDuplicates(dupThreshold);
		// We know it's the 3 para that is a duplicate
		Assert.assertEquals(5.982905982905983, cont.getContentParagraphs().get(2).getSimRat(), 0.0);
		// We expect that in clean up, this para will be removed.
		String actual = cont.getCleanContent(dupThreshold);
		// We expect 4, because while we don't include the duplicate para in the cleanup, it is still part
		// of the
		// object
		Assert.assertEquals(4, cont.getContentParagraphs().size());
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testTextContentRateDups2Sim() throws IOException {
		String expected = io.fileToString("src/test/resources/unit_test_files/TextContent/Exp.Similar.Multiple.txt");
		double dupThreshold = 30.0;
		String title = "Normal Full Content";
		String rawContent = io.fileToString("src/test/resources/unit_test_files/TextContent/Similar.Multiple.txt");
		TextContent cont = new TextContent(title, rawContent);
		cont.rateDuplicates(dupThreshold);

		// We know it's the 3 and 10 para that is a duplicate
		Assert.assertEquals(6.532663316582915, cont.getContentParagraphs().get(2).getSimRat(), 0.0);
		Assert.assertEquals(1.9607843137254901, cont.getContentParagraphs().get(9).getSimRat(), 0.0);

		// We expect that in clean up, this para will be removed.
		String actual = cont.getCleanContent(dupThreshold);
		// We expect 11, because while we don't include the duplicate para in the cleanup, it is still part
		// of the object
		Assert.assertEquals(11, cont.getContentParagraphs().size());
		Assert.assertEquals(expected + "</p>", actual);
	}

	@Test
	public void testTextContentRateDups1SimPastThreshold() throws IOException {
		String expected = io.fileToString("src/test/resources/unit_test_files/TextContent/SimilarFullCont.txt");
		double dupThreshold = 4.0;
		String title = "Normal Full Content";
		String rawContent = io.fileToString("src/test/resources/unit_test_files/TextContent/SimilarFullCont.txt");
		TextContent cont = new TextContent(title, rawContent);
		cont.rateDuplicates(dupThreshold);
		// We know it's the 3 para that is a duplicate
		Assert.assertEquals(-1.0, cont.getContentParagraphs().get(2).getSimRat(), 0.0);
		// We expect that in clean up, this para will be removed.
		String actual = cont.getCleanContent(dupThreshold);
		// We expect 4, because while we don't include the duplicate para in the cleanup, it is still part
		// of the
		// object
		Assert.assertEquals(4, cont.getContentParagraphs().size());
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testTextContentWordCount() throws IOException {
		String title = "Normal Full Content";
		String rawContent = io.fileToString("src/test/resources/unit_test_files/TextContent/NormalFullContent.txt");
		TextContent cont = new TextContent(title, rawContent);
		Assert.assertEquals(43, cont.getContentParagraphs().get(0).getWordCount());
		Assert.assertEquals(58, cont.getContentParagraphs().get(1).getWordCount());
		Assert.assertEquals(70, cont.getContentParagraphs().get(2).getWordCount());
		Assert.assertEquals(171, cont.getWordCount());
	}

	@Test
	public void testTextContentRateDups1DupWithWordCount() throws IOException {
		String expected = io.fileToString("src/test/resources/unit_test_files/TextContent/Exp.NormalFullContent.txt");
		double dupThreshold = 30.0;
		String title = "Normal Full Content";
		String rawContent = io.fileToString("src/test/resources/unit_test_files/TextContent/DuplicateFullCont.txt");
		TextContent cont = new TextContent(title, rawContent);
		String actual = cont.getCleanContent(dupThreshold);
		// We expect 4, because while we don't include the duplicate para in the cleanup, it is still part
		// of the
		// object
		Assert.assertEquals(4, cont.getContentParagraphs().size());
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(171, cont.getWordCount());
	}

	@Test
	public void testTextContentSentenceCount() throws IOException {
		String title = "Normal Full Content";
		String rawContent = io.fileToString("src/test/resources/unit_test_files/TextContent/NormalFullContent.txt");
		TextContent cont = new TextContent(title, rawContent);
		Assert.assertEquals(3, cont.getContentParagraphs().get(0).getSentenceCount());
	}

	@Test
	public void testTextSentence() throws IOException {
		
		String [] rawContent = {
				"Welcome to Edition 1.12 of the Rocket Report! This week we have all kinds of stories about small rockets, the scoop on a Texas rocket company back from the dead, and some commercial crew launch dates that we may believe. Or maybe not.", 
				"As always, we welcome reader submissions, and if you don't want to miss an issue, please subscribe using the box below (the form will not appear on AMP-enabled versions of the site). Each report will include information on small-, medium-, and heavy-lift rockets as well as a quick look ahead at the next three launches on the calendar.",
				"New report quantifies surge in small rockets. In an updated report on the state of the small-satellite launch industry, Carlos Niederstrasser quantifies the increase in potential small launch vehicle contenders, defined as rockets capable of carrying up to 1000kg to low-Earth orbit. The growth has been remarkable. \"The total number of efforts we are tracking... has increased from a mere 31 in 2015 to over 101 in 2018,\" he writes."
		};
		String expected = io.fileToString("src/test/resources/unit_test_files/TextContent/Exp.NormalFullContent.txt");
		double dupThreshold = 30.0;
		String title = "Normal Full Content";
		TextContent cont = new TextContent(title, rawContent);
		String actual = cont.getCleanContent(dupThreshold);
		Assert.assertEquals(3, cont.getContentParagraphs().size());
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testTextSentenceRateDups() throws IOException {
		String[] rawContent = {
		        "Welcome to Edition 1.12 of the Rocket Report! This week we have all kinds of stories about small rockets, the scoop on a Texas rocket company back from the dead, and some commercial crew launch dates that we may believe. Or maybe not.",
		        "As always, we welcome reader submissions, and if you don't want to miss an issue, please subscribe using the box below (the form will not appear on AMP-enabled versions of the site). Each report will include information on small-, medium-, and heavy-lift rockets as well as a quick look ahead at the next three launches on the calendar.",
		        "New report quantifies surge in small rockets. In an updated report on the state of the small-satellite launch industry, Carlos Niederstrasser quantifies the increase in potential small launch vehicle contenders, defined as rockets capable of carrying up to 1000kg to low-Earth orbit. The growth has been remarkable. \"The total number of efforts we are tracking... has increased from a mere 31 in 2015 to over 101 in 2018,\" he writes."
		};
		double dupThreshold = 30.0;
		String title = "Normal Full Content";
		TextContent cont = new TextContent(title, rawContent);
		cont.rateDuplicates(dupThreshold);
		for(ContentParagraph para : cont.getContentParagraphs()) {
			Assert.assertEquals(-1.0, para.getSimRat(), 0.0);
		}
	}

	@Test
	public void testTextSentenceRateDups1Dup() throws IOException {
		String[] rawContent = {
				"Welcome to Edition 1.12 of the Rocket Report! This week we have all kinds of stories about small rockets, the scoop on a Texas rocket company back from the dead, and some commercial crew launch dates that we may believe. Or maybe not.",
				"As always, we welcome reader submissions, and if you don't want to miss an issue, please subscribe using the box below (the form will not appear on AMP-enabled versions of the site). Each report will include information on small-, medium-, and heavy-lift rockets as well as a quick look ahead at the next three launches on the calendar.",
				"Welcome to Edition 1.12 of the Rocket Report! This week we have all kinds of stories about small rockets, the scoop on a Texas rocket company back from the dead, and some commercial crew launch dates that we may believe. Or maybe not.",
		        "New report quantifies surge in small rockets. In an updated report on the state of the small-satellite launch industry, Carlos Niederstrasser quantifies the increase in potential small launch vehicle contenders, defined as rockets capable of carrying up to 1000kg to low-Earth orbit. The growth has been remarkable. \"The total number of efforts we are tracking... has increased from a mere 31 in 2015 to over 101 in 2018,\" he writes."
		};
		String expected = io.fileToString("src/test/resources/unit_test_files/TextContent/Exp.NormalFullContent.txt");
		double dupThreshold = 30.0;
		String title = "Normal Full Content";
		TextContent cont = new TextContent(title, rawContent);
		cont.rateDuplicates(dupThreshold);
		// We know it's the 3 para that is a duplicate
		Assert.assertEquals(0.0, cont.getContentParagraphs().get(2).getSimRat(), 0.0);
		// We expect that in clean up, this para will be removed.
		String actual = cont.getCleanContent(dupThreshold);
		// We expect 4, because while we don't include the duplicate para in the cleanup, it is still part
		// of the
		// object
		Assert.assertEquals(4, cont.getContentParagraphs().size());
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testTextSentenceRateDups1Sim() throws IOException {
		String[] rawContent = {
		        "Welcome to Edition 1.12 of the Rocket Report! This week we have all kinds of stories about small rockets, the scoop on a Texas rocket company back from the dead, and some commercial crew launch dates that we may believe. Or maybe not.",
		        "As always, we welcome reader submissions, and if you don't want to miss an issue, please subscribe using the box below (the form will not appear on AMP-enabled versions of the site). Each report will include information on small-, medium-, and heavy-lift rockets as well as a quick look ahead at the next three launches on the calendar.",
		        "Welcome to Edition 1.11 of the Rocket Report! This week we have all types of stories about small rockets, the scoop on a Texas rocket company back to the living, and some commercial crew launch dates that we may believe. Or maybe not.",
		        "New report quantifies surge in small rockets. In an updated report on the state of the small-satellite launch industry, Carlos Niederstrasser quantifies the increase in potential small launch vehicle contenders, defined as rockets capable of carrying up to 1000kg to low-Earth orbit. The growth has been remarkable. \"The total number of efforts we are tracking... has increased from a mere 31 in 2015 to over 101 in 2018,\" he writes."
		};
		String expected = io.fileToString("src/test/resources/unit_test_files/TextContent/Exp.NormalFullContent.txt");
		double dupThreshold = 30.0;
		String title = "Normal Full Content";
		TextContent cont = new TextContent(title, rawContent);
		cont.rateDuplicates(dupThreshold);
		// We know it's the 3 para that is a duplicate
		Assert.assertEquals(5.982905982905983, cont.getContentParagraphs().get(2).getSimRat(), 0.0);
		// We expect that in clean up, this para will be removed.
		String actual = cont.getCleanContent(dupThreshold);
		// We expect 4, because while we don't include the duplicate para in the cleanup, it is still part
		// of the
		// object
		Assert.assertEquals(4, cont.getContentParagraphs().size());
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testTextSentenceRateDups2Sim() throws IOException {
		String[] rawContent = {
		        "Sen. Bernie Sanders' \"Medicare for all\" plan would increase government healthcare spending by $32.6 trillion over 10 years, according to a study by a university-based libertarian policy center.",
		        "Sanders called the report \"grossly misleading and biased\" in a statement, but his office has not conducted its own cost analysis.",
		        "WASHINGTON — Sen. Bernie Sanders' \"Medicare for all\" plan would increase government healthcare spending by $32.6 trillion over 10 years, according to a study by a university-based libertarian policy center.",
		        "The latest plan from the Vermont independent would require historic tax increases as government replaces what employers and consumers now pay for healthcare, according to the analysis being released Monday by the Mercatus Center at George Mason University in Virginia.",
		        "Sanders' plan builds on Medicare, the popular insurance program for seniors.",
		        "Responding to the study, Sanders took aim at the Mercatus Center, which receives funding from the conservative Koch brothers.",
		        "However, the Mercatus estimates are within the range of other cost projections for Sanders' 2016 plan.",
		        "The Mercatus study takes issue with a key cost-saving feature of the plan: that hospitals and doctors would accept payment based on lower Medicare rates for all their patients.",
		        "The study found that the plan would reap substantial savings from lower prescription costs — $846 billion over 10 years — since the government would deal directly with drugmakers.",
		        "However, the Mercatus estimates are within the range of other costs projections for Sanders' 2015 plan.",
		        "After taking into account current government healthcare financing, the study estimated that doubling all federal individual and corporate income taxes would not fully cover the additional costs."
		};
		String expected = io.fileToString("src/test/resources/unit_test_files/TextContent/Exp.Similar.Multiple.txt");
		double dupThreshold = 30.0;
		String title = "Normal Full Content";
		TextContent cont = new TextContent(title, rawContent);
		cont.rateDuplicates(dupThreshold);

		// We know it's the 3 and 10 para that is a duplicate
		Assert.assertEquals(6.532663316582915, cont.getContentParagraphs().get(2).getSimRat(), 0.0);
		Assert.assertEquals(1.9607843137254901, cont.getContentParagraphs().get(9).getSimRat(), 0.0);

		// We expect that in clean up, this para will be removed.
		String actual = cont.getCleanContent(dupThreshold);
		// We expect 11, because while we don't include the duplicate para in the cleanup, it is still part
		// of the object
		Assert.assertEquals(11, cont.getContentParagraphs().size());
		Assert.assertEquals(expected + "</p>", actual);
	}

	@Test
	public void testTextSentenceRateDups1SimPastThreshold() throws IOException {
		String[] rawContent = {
		        "Welcome to Edition 1.12 of the Rocket Report! This week we have all kinds of stories about small rockets, the scoop on a Texas rocket company back from the dead, and some commercial crew launch dates that we may believe. Or maybe not.",
		        "As always, we welcome reader submissions, and if you don't want to miss an issue, please subscribe using the box below (the form will not appear on AMP-enabled versions of the site). Each report will include information on small-, medium-, and heavy-lift rockets as well as a quick look ahead at the next three launches on the calendar.",
		        "Welcome to Edition 1.11 of the Rocket Report! This week we have all types of stories about small rockets, the scoop on a Texas rocket company back to the living, and some commercial crew launch dates that we may believe. Or maybe not.",
		        "New report quantifies surge in small rockets. In an updated report on the state of the small-satellite launch industry, Carlos Niederstrasser quantifies the increase in potential small launch vehicle contenders, defined as rockets capable of carrying up to 1000kg to low-Earth orbit. The growth has been remarkable. \"The total number of efforts we are tracking... has increased from a mere 31 in 2015 to over 101 in 2018,\" he writes."
		};
		String expected = io.fileToString("src/test/resources/unit_test_files/TextContent/SimilarFullCont.txt");
		double dupThreshold = 4.0;
		String title = "Normal Full Content";
		TextContent cont = new TextContent(title, rawContent);
		cont.rateDuplicates(dupThreshold);
		// We know it's the 3 para that is a duplicate
		Assert.assertEquals(-1.0, cont.getContentParagraphs().get(2).getSimRat(), 0.0);
		// We expect that in clean up, this para will be removed.
		String actual = cont.getCleanContent(dupThreshold);
		// We expect 4, because while we don't include the duplicate para in the cleanup, it is still part
		// of the
		// object
		Assert.assertEquals(4, cont.getContentParagraphs().size());
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testTextSentenceWordCount() throws IOException {
		String[] rawContent = {
		        "Welcome to Edition 1.12 of the Rocket Report! This week we have all kinds of stories about small rockets, the scoop on a Texas rocket company back from the dead, and some commercial crew launch dates that we may believe. Or maybe not.",
		        "As always, we welcome reader submissions, and if you don't want to miss an issue, please subscribe using the box below (the form will not appear on AMP-enabled versions of the site). Each report will include information on small-, medium-, and heavy-lift rockets as well as a quick look ahead at the next three launches on the calendar.",
		        "New report quantifies surge in small rockets. In an updated report on the state of the small-satellite launch industry, Carlos Niederstrasser quantifies the increase in potential small launch vehicle contenders, defined as rockets capable of carrying up to 1000kg to low-Earth orbit. The growth has been remarkable. \"The total number of efforts we are tracking... has increased from a mere 31 in 2015 to over 101 in 2018,\" he writes."
		};
		String title = "Normal Full Content";
		TextContent cont = new TextContent(title, rawContent);
		Assert.assertEquals(43, cont.getContentParagraphs().get(0).getWordCount());
		Assert.assertEquals(58, cont.getContentParagraphs().get(1).getWordCount());
		Assert.assertEquals(70, cont.getContentParagraphs().get(2).getWordCount());
		Assert.assertEquals(171, cont.getWordCount());
	}

	@Test
	public void testTextSentenceRateDups1DupWordCount() throws IOException {
		String[] rawContent = {
		        "Welcome to Edition 1.12 of the Rocket Report! This week we have all kinds of stories about small rockets, the scoop on a Texas rocket company back from the dead, and some commercial crew launch dates that we may believe. Or maybe not.",
		        "As always, we welcome reader submissions, and if you don't want to miss an issue, please subscribe using the box below (the form will not appear on AMP-enabled versions of the site). Each report will include information on small-, medium-, and heavy-lift rockets as well as a quick look ahead at the next three launches on the calendar.",
		        "Welcome to Edition 1.12 of the Rocket Report! This week we have all kinds of stories about small rockets, the scoop on a Texas rocket company back from the dead, and some commercial crew launch dates that we may believe. Or maybe not.",
		        "New report quantifies surge in small rockets. In an updated report on the state of the small-satellite launch industry, Carlos Niederstrasser quantifies the increase in potential small launch vehicle contenders, defined as rockets capable of carrying up to 1000kg to low-Earth orbit. The growth has been remarkable. \"The total number of efforts we are tracking... has increased from a mere 31 in 2015 to over 101 in 2018,\" he writes."
		};
		String expected = io.fileToString("src/test/resources/unit_test_files/TextContent/Exp.NormalFullContent.txt");
		double dupThreshold = 30.0;
		String title = "Normal Full Content";
		TextContent cont = new TextContent(title, rawContent);
		cont.rateDuplicates(dupThreshold);
		// We expect that in clean up, this para will be removed.
		String actual = cont.getCleanContent(dupThreshold);
		// We expect 4, because while we don't include the duplicate para in the cleanup, it is still part
		// of the object
		Assert.assertEquals(4, cont.getContentParagraphs().size());
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(171, cont.getWordCount());
	}

	@Test
	public void testTextSentenceRateDups1DupWordCount2() throws IOException {
		String[] rawContent = {
		        "Welcome to Edition 1.12 of the Rocket Report! This week we have all kinds of stories about small rockets, the scoop on a Texas rocket company back from the dead, and some commercial crew launch dates that we may believe. Or maybe not.",
		        "As always, we welcome reader submissions, and if you don't want to miss an issue, please subscribe using the box below (the form will not appear on AMP-enabled versions of the site). Each report will include information on small-, medium-, and heavy-lift rockets as well as a quick look ahead at the next three launches on the calendar.",
		        "Welcome to Edition 1.12 of the Rocket Report! This week we have all kinds of stories about small rockets, the scoop on a Texas rocket company back from the dead, and some commercial crew launch dates that we may believe. Or maybe not.",
		        "New report quantifies surge in small rockets. In an updated report on the state of the small-satellite launch industry, Carlos Niederstrasser quantifies the increase in potential small launch vehicle contenders, defined as rockets capable of carrying up to 1000kg to low-Earth orbit. The growth has been remarkable. \"The total number of efforts we are tracking... has increased from a mere 31 in 2015 to over 101 in 2018,\" he writes."
		};
		double dupThreshold = 30.0;
		String title = "Normal Full Content";
		TextContent cont = new TextContent(title, rawContent);
		cont.rateDuplicates(dupThreshold);
		// We expect that in clean up, this para will be removed.
		cont.getCleanSentences(dupThreshold);
		// We expect 4, because while we don't include the duplicate para in the cleanup, it is still part
		// of the object
		Assert.assertEquals(171, cont.getWordCount());
	}

	@Test
	public void testTextSentenceSentenceCount() throws IOException {
		String[] rawContent = {
		        "Welcome to Edition 1.12 of the Rocket Report! This week we have all kinds of stories about small rockets, the scoop on a Texas rocket company back from the dead, and some commercial crew launch dates that we may believe. Or maybe not.",
		        "As always, we welcome reader submissions, and if you don't want to miss an issue, please subscribe using the box below (the form will not appear on AMP-enabled versions of the site). Each report will include information on small-, medium-, and heavy-lift rockets as well as a quick look ahead at the next three launches on the calendar.",
		        "New report quantifies surge in small rockets. In an updated report on the state of the small-satellite launch industry, Carlos Niederstrasser quantifies the increase in potential small launch vehicle contenders, defined as rockets capable of carrying up to 1000kg to low-Earth orbit. The growth has been remarkable. \"The total number of efforts we are tracking... has increased from a mere 31 in 2015 to over 101 in 2018,\" he writes."
		};
		String title = "Normal Full Content";
		TextContent cont = new TextContent(title, rawContent);
		Assert.assertEquals(3, cont.getContentParagraphs().get(0).getSentenceCount());
	}

	@Test
	public void testTextSentenceCleanSentences() throws IOException {
		String[] rawContent = {
		        "Sen. Bernie Sanders' \"Medicare for all\" plan would increase government healthcare spending by $32.6 trillion over 10 years, according to a study by a university-based libertarian policy center.",
		        "Sanders called the report \"grossly misleading and biased\" in a statement, but his office has not conducted its own cost analysis.",
		        "WASHINGTON — Sen. Bernie Sanders' \"Medicare for all\" plan would increase government healthcare spending by $32.6 trillion over 10 years, according to a study by a university-based libertarian policy center.",
		        "The latest plan from the Vermont independent would require historic tax increases as government replaces what employers and consumers now pay for healthcare, according to the analysis being released Monday by the Mercatus Center at George Mason University in Virginia.",
		        "Sanders' plan builds on Medicare, the popular insurance program for seniors.",
		        "Responding to the study, Sanders took aim at the Mercatus Center, which receives funding from the conservative Koch brothers.",
		        "However, the Mercatus estimates are within the range of other cost projections for Sanders' 2016 plan.",
		        "The Mercatus study takes issue with a key cost-saving feature of the plan: that hospitals and doctors would accept payment based on lower Medicare rates for all their patients.",
		        "The study found that the plan would reap substantial savings from lower prescription costs — $846 billion over 10 years — since the government would deal directly with drugmakers.",
		        "However, the Mercatus estimates are within the range of other costs projections for Sanders' 2015 plan.",
		        "After taking into account current government healthcare financing, the study estimated that doubling all federal individual and corporate income taxes would not fully cover the additional costs."
		};
		String expected = io.fileToString("src/test/resources/unit_test_files/TextContent/Exp.Similar.Multiple.txt");
		double dupThreshold = 30.0;
		String title = "Normal Full Content";
		TextContent cont = new TextContent(title, rawContent);

		// We expect that in clean up, this para will be removed.
		String[] actual = cont.getCleanSentences(dupThreshold);
		// We expect 11, because while we don't include the duplicate para in the cleanup, it is still part
		// of the object
		Assert.assertEquals(9, actual.length);
		// Assert.assertEquals(expected + "</p>", actual);
	}
}
