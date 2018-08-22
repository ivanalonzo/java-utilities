package co.alonsos.java_utilities.lang;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ContentTest {

	@Test
	public void testHasNoSents() throws Exception {
		Content cont = new Content();
		cont.setTitle("This is the title");
		cont.setContent("This is the content");
		Assert.assertEquals(false, cont.isHasSentences());
	}

	@Test
	public void testHasSents() throws Exception {
		String[] sents = {
				"First para",
				"Second para"
		};
		Content cont = new Content();
		cont.setTitle("This is the title");
		cont.setSentences(sents);
		Assert.assertEquals(true, cont.isHasSentences());
	}

	@Test
	public void testContandSents() {
		Content cont = new Content();
		String[] sents = {
		        "First para", "Second para"
		};
		try {
			cont.setTitle("This is the title");
			cont.setContent("This is the content");
			cont.setSentences(sents);
		}catch (Exception e) {
			Assert.assertEquals(cont.CONTENT_SET, e.getMessage());
		}
	}

	@Test
	public void testSentsandCont() {
		Content cont = new Content();
		String[] sents = {
		        "First para", "Second para"
		};
		try {
			cont.setTitle("This is the title");
			cont.setSentences(sents);
			cont.setContent("This is the content");
		}catch (Exception e) {
			Assert.assertEquals(cont.SENTENCES_SET, e.getMessage());
		}
	}

}
