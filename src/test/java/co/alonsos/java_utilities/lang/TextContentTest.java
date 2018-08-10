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
//		for(ContentParagraph para : cont.getContentParagraphs()) {
//			log.debug(para.getSimRat());
//		}

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
	public void testTextContentSentenceCount() throws IOException {
		String title = "Normal Full Content";
		String rawContent = io.fileToString("src/test/resources/unit_test_files/TextContent/NormalFullContent.txt");
		TextContent cont = new TextContent(title, rawContent);
		Assert.assertEquals(3, cont.getContentParagraphs().get(0).getSentenceCount());
	}
}
