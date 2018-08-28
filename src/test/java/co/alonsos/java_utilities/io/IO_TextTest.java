package co.alonsos.java_utilities.io;

import java.io.IOException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IO_TextTest {
	IO_Text text;
	IO_Utils io = new IO_Utils();
	private static Logger log = Logger.getLogger(IO_TextTest.class);

	@BeforeEach
	public void setup() {
		text = new IO_Text();
	}

	@AfterEach
	public void tearDown() {
		text = null;
	}

	@Test
	public void testStripStatic() throws Exception {
		String input = "<div class=\"asset-double-wide double-wide p402_premium\"><p class=\"speakable-p-1 p-text\">MADRID (AP) &#x2014; Formula One teams will finally take their 2018 cars to the track when preseason testing begins Monday in Spain, with Mercedes and Ferrari promising significant improvements to try to keep running up front.</p><p class=\"speakable-p-2 p-text\">This week&apos;s test session in Barcelona will also allow McLaren to debut its Renault engine after three dismal years with Honda, and give Red Bull a chance to show it has a car fast enough to finally contend for titles again.</p><p class=\"p-text\">Force India and Williams will look to move closer to the top three, while Renault and Hass will try to keep improving. Toro Rosso will be the team trying to succeed with the Honda engine, and Sauber will bring partner Alfa Romeo back to F1 hoping to take a step forward after a last-place finish a year ago.</p><p class=\"p-text\">What all teams will have is the halo, the protective cockpit device that is mandatory this season. The odd-looking cover that goes around the drivers&apos; heads is the most visible change to cars from a year ago. The &quot;shark fins&quot; and their adjacent tiny wings on top of the cars are gone this season, and each driver will only be allowed to use three power units &#x2014; instead of four &#x2014; during the 21-race calendar.</p><p class=\"p-text\">The season opens on March 25 in Australia. Drivers will participate in another four-day test session in Barcelona from March 6-9. Most teams unveiled their cars last week, when drivers were allowed to get a first taste of action in limited outings for video filming.</p><p class=\"p-text\">Mercedes dominated the last four seasons but Lewis Hamilton faced a tough challenge from Ferrari&apos;s Sebastian Vettel a year ago.</p><p class=\"p-text\">&quot;Last year the car was fantastic, but there were so many things we could still improve on,&quot; Hamilton said. &quot;What we&apos;ve done is taken a lot of the DNA from last year&apos;s car... this is the sister car, an evolution of that. This is better than last year&apos;s car in every aspect. It looks quite similar but inside, underneath the shell, and even the bodywork, it&apos;s all refined to perform even better than it did last year.&quot;</p><p class=\"p-text\">Ferrari won five races with Vettel last season, but the Italian team hasn&apos;t won a drivers&apos; title since 2007 with Kimi Raikkonen. Its last constructors&apos; championship came in 2008.</p><p class=\"p-text\">&quot;We can&apos;t wait to get out on to the track to see how the car performs, how it behaves,&quot; Vettel said. &quot;This car is a big step from last year.&quot;</p><p class=\"p-text\">Red Bull, which won four straight titles with Vettel from 2010-13, was the third force last season and at times it got extremely close to Mercedes and Ferrari. Max Verstappen won two races and Daniel Ricciardo one. Red Bull will debut a new suspension &#x2014; in addition to new livery &#x2014; that it hopes will allow the team to make a leap forward and become a title contender.</p><p class=\"p-text\">McLaren is likely the team most anxious to get to the track after swapping from Honda to Renault, a move it hopes will end the struggles from the last few seasons, when it dealt with reliability issues and lack of power.</p><p class=\"p-text\">&quot;I think good times are coming,&quot; two-time world champion Fernando Alonso told Sky Sports. &quot;We struggled, we kept motivation very high. I think the team is stronger now than three years ago. What doesn&apos;t kill you makes you stronger ... we will see the first results this year.&quot;</p><p class=\"p-text\">There will be two rookie drivers on the grid: Russian Sergey Sirotkin with Williams and Monaco-born Charles Leclerc with Sauber, which is actually calling itself Alfa Romeo Sauber F1 Team after inking a technical and commercial partnership with the Italian brand that has a long F1 history.</p><p class=\"p-text\">Polish driver Robert Kubica, who hasn&apos;t raced in F1 since 2010 after being badly injured in a rally crash, signed as a reserve driver with Williams and is expected to participate in three practice sessions this season.</p><p class=\"p-text\">&quot;We won the championship last year but it was difficult at times, some really strong opposition,&quot; Mercedes chief Toto Wolff said. &quot;We needed to find the right balance between developing our car without losing its raw speed. Last year&apos;s car was the fastest car on track &#x2014; it won the most races and had the most pole positions. We needed to preserve that.&quot;</p><p class=\"p-text\">___</p><p class=\"p-text p-text-last\">Tales Azzoni on Twitter: http://twitter.com/tazzoni</p><p><em>Copyright 2018 The Associated Press. All rights reserved. This material may not be published, broadcast, rewritten or redistributed.</em></p></div>";
		String expected = "MADRID (AP)  -  Formula One teams will finally take their 2018 cars to the track when preseason testing begins Monday in Spain, with Mercedes and Ferrari promising significant improvements to try to keep running up front.  This week's test session in Barcelona will also allow McLaren to debut its Renault engine after three dismal years with Honda, and give Red Bull a chance to show it has a car fast enough to finally contend for titles again.  Force India and Williams will look to move closer to the top three, while Renault and Hass will try to keep improving. Toro Rosso will be the team trying to succeed with the Honda engine, and Sauber will bring partner Alfa Romeo back to F1 hoping to take a step forward after a last-place finish a year ago.  What all teams will have is the halo, the protective cockpit device that is mandatory this season. The odd-looking cover that goes around the drivers' heads is the most visible change to cars from a year ago. The \"shark fins\" and their adjacent tiny wings on top of the cars are gone this season, and each driver will only be allowed to use three power units  -  instead of four  -  during the 21-race calendar.  The season opens on March 25 in Australia. Drivers will participate in another four-day test session in Barcelona from March 6-9. Most teams unveiled their cars last week, when drivers were allowed to get a first taste of action in limited outings for video filming.  Mercedes dominated the last four seasons but Lewis Hamilton faced a tough challenge from Ferrari's Sebastian Vettel a year ago.  \"Last year the car was fantastic, but there were so many things we could still improve on,\" Hamilton said. \"What we've done is taken a lot of the DNA from last year's car... this is the sister car, an evolution of that. This is better than last year's car in every aspect. It looks quite similar but inside, underneath the shell, and even the bodywork, it's all refined to perform even better than it did last year.\"  Ferrari won five races with Vettel last season, but the Italian team hasn't won a drivers' title since 2007 with Kimi Raikkonen. Its last constructors' championship came in 2008.  \"We can't wait to get out on to the track to see how the car performs, how it behaves,\" Vettel said. \"This car is a big step from last year.\"  Red Bull, which won four straight titles with Vettel from 2010-13, was the third force last season and at times it got extremely close to Mercedes and Ferrari. Max Verstappen won two races and Daniel Ricciardo one. Red Bull will debut a new suspension  -  in addition to new livery  -  that it hopes will allow the team to make a leap forward and become a title contender.  McLaren is likely the team most anxious to get to the track after swapping from Honda to Renault, a move it hopes will end the struggles from the last few seasons, when it dealt with reliability issues and lack of power.  \"I think good times are coming,\" two-time world champion Fernando Alonso told Sky Sports. \"We struggled, we kept motivation very high. I think the team is stronger now than three years ago. What doesn't kill you makes you stronger ... we will see the first results this year.\"  There will be two rookie drivers on the grid: Russian Sergey Sirotkin with Williams and Monaco-born Charles Leclerc with Sauber, which is actually calling itself Alfa Romeo Sauber F1 Team after inking a technical and commercial partnership with the Italian brand that has a long F1 history.  Polish driver Robert Kubica, who hasn't raced in F1 since 2010 after being badly injured in a rally crash, signed as a reserve driver with Williams and is expected to participate in three practice sessions this season.  \"We won the championship last year but it was difficult at times, some really strong opposition,\" Mercedes chief Toto Wolff said. \"We needed to find the right balance between developing our car without losing its raw speed. Last year's car was the fastest car on track  -  it won the most races and had the most pole positions. We needed to preserve that.\"  ___  Tales Azzoni on Twitter: http://twitter.com/tazzoni   Copyright 2018 The Associated Press. All rights reserved. This material may not be published, broadcast, rewritten or redistributed.";
		String converted = text.cleanHTMLTagsAndEncoding(input, null);
		Assert.assertEquals(expected, converted);
	}

	@Test
	public void testStripDynamic() throws Exception {
		String input = "remove these characeter and replace with \" other chars";
		String expected = "these characeter and replace with ' other chars";
		HashMap<String, String> cleanup = new HashMap<String, String>();
		cleanup.put("remove", "");
		cleanup.put("\"", "'");
		String converted = text.cleanHTMLTagsAndEncoding(input, cleanup);
		Assert.assertEquals(expected, converted);
	}

	@Test
	public void testConvertToHTML() throws Exception {
		String input = "Convert \n to HTML breaks";
		String expected = "Convert <br> to HTML breaks";
		String converted = text.convertToHTML(input);
		Assert.assertEquals(expected, converted);
	}

	@Test
	public void testCleanText() throws Exception {
		String input = io.fileToString("src/test/resources/unit_test_files/TabsInText.txt");
		String expected = "Marooned motorists, transport woes, after floods in DC Chantalle Edmunds @chantallenews April 16, 2018 10:50 am04/16/2018 gave way to localized flash flooding Monday and caused large pools of standing water in the D.C. area, with drainage proving a problem on the region's roadways.";
		String actual = text.cleanText(input, true);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCleanTextRemoveNewLines() throws Exception {
		String input = io.fileToString("src/test/resources/unit_test_files/NewLinesInText.txt");
		String expected = "Including speaking with witnesses and reading the autopsy and police reports. I got to a place where I had to see Sirhan, Kennedy said. He would not discuss the specifics of their conversation";
		String actual = text.cleanText(input, true);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCleanTextIgnoreNewLines() throws Exception {
		String input = io.fileToString("src/test/resources/unit_test_files/NewLinesInText.txt");
		String expected = "Including speaking with</p> witnesses and reading the autopsy and police reports.</p> I got to a place where I had to see Sirhan, Kennedy said. He would not discuss the specifics of their conversation";
		String actual = text.cleanText(input, false);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void wordCounterFile() throws Exception {
		String filePath = "src/test/resources/unit_test_files/file197.txt";
		Assert.assertEquals(197, text.wordCount(filePath));
	}

	@Test
	public void wordCounterString() throws Exception {
		String filePath = "These are words and there should be 8";
		Assert.assertEquals(8, text.wordCount(filePath));
	}

	@Test
	public void wordCounterEmpty() throws Exception {
		Assert.assertEquals(0, text.wordCount(""));
	}

	@Test
	public void wordCounterNull() throws Exception {
		Assert.assertEquals(0, text.wordCount(null));
	}

	@Test
	public void convertArrayToParagraph() throws Exception {
		String[] sentences = {
				"EAST WENATCHEE,  Washington  - Hands on the   wheel, eyes squinting against the winter sun, Lauren Miehe eases his Land Rover down the main drag and tells me how he used to spot promising sites to build a bitcoin mine, back in 2013, when he was a freshly arrived techie from Seattle and had just discovered this sleepy rural community.",
				"Bitcoin mining - the complex process in which computers solve a complicated math puzzle to win a stack of virtual currency - uses an inordinate amount of electricity, and thanks to five hydroelectric dams that straddle this stretch of the river, about three hours east of Seattle, miners could buy that power more cheaply here than anywhere else in the nation.",
				"Long before locals had even heard the words \"cryptocurrency\" or \"blockchain,\" Miehe and his peers realized that this semi-arid agricultural region known as the Mid-Columbia Basin was the best place to mine bitcoin in America - and maybe the world.",
				"Outsiders are so eager to turn the basin's power into cryptocurrency that this winter, several would-be miners from Asia flew their private jet into the local airport, took a rental car to one of the local dams, and, according to a utility official, politely informed staff at the dam visitors center, \"We want to see the dam master because we want to buy some electricity.\"" };
		String expected = 
				"EAST WENATCHEE,  Washington  - Hands on the   wheel, eyes squinting against the winter sun, Lauren Miehe eases his Land Rover down the main drag and tells me how he used to spot promising sites to build a bitcoin mine, back in 2013, when he was a freshly arrived techie from Seattle and had just discovered this sleepy rural community.\n\n" + 
				"Bitcoin mining - the complex process in which computers solve a complicated math puzzle to win a stack of virtual currency - uses an inordinate amount of electricity, and thanks to five hydroelectric dams that straddle this stretch of the river, about three hours east of Seattle, miners could buy that power more cheaply here than anywhere else in the nation.\n\n" + 
				"Long before locals had even heard the words \"cryptocurrency\" or \"blockchain,\" Miehe and his peers realized that this semi-arid agricultural region known as the Mid-Columbia Basin was the best place to mine bitcoin in America - and maybe the world.\n\n" + 
				"Outsiders are so eager to turn the basin's power into cryptocurrency that this winter, several would-be miners from Asia flew their private jet into the local airport, took a rental car to one of the local dams, and, according to a utility official, politely informed staff at the dam visitors center, \"We want to see the dam master because we want to buy some electricity.\""
				+ "\n\n";
		
		String converted = text.stringArrayToParagraph(sentences);
		Assert.assertEquals(expected, converted);
		
	}
	
	@Test
	public void convertArrayToParagraphNull() {
		Assert.assertEquals("", text.stringArrayToParagraph(null));
		String[] input = {};
		Assert.assertEquals("", text.stringArrayToParagraph(input));
	}

	@Test
	public void convertArrayToParagraphWithMax() throws Exception {
		String[] sentences = {
		        "EAST WENATCHEE,  Washington  - Hands on the   wheel, eyes squinting against the winter sun, Lauren Miehe eases his Land Rover down the main drag and tells me how he used to spot promising sites to build a bitcoin mine, back in 2013, when he was a freshly arrived techie from Seattle and had just discovered this sleepy rural community.",
		        "Bitcoin mining - the complex process in which computers solve a complicated math puzzle to win a stack of virtual currency - uses an inordinate amount of electricity, and thanks to five hydroelectric dams that straddle this stretch of the river, about three hours east of Seattle, miners could buy that power more cheaply here than anywhere else in the nation.",
		        "Long before locals had even heard the words \"cryptocurrency\" or \"blockchain,\" Miehe and his peers realized that this semi-arid agricultural region known as the Mid-Columbia Basin was the best place to mine bitcoin in America - and maybe the world.",
		        "Outsiders are so eager to turn the basin's power into cryptocurrency that this winter, several would-be miners from Asia flew their private jet into the local airport, took a rental car to one of the local dams, and, according to a utility official, politely informed staff at the dam visitors center, \"We want to see the dam master because we want to buy some electricity.\""
		};
		String expected = "EAST WENATCHEE,  Washington  - Hands on the   wheel, eyes squinting against the winter sun, Lauren Miehe eases his Land Rover down the main drag and tells me how he used to spot promising sites to build a bitcoin mine, back in 2013, when he was a freshly arrived techie from Seattle and had just discovered this sleepy rural community.\n\n"
		        + "Bitcoin mining - the complex process in which computers solve a complicated math puzzle to win a stack of virtual currency - uses an inordinate amount of electricity, and thanks to five hydroelectric dams that straddle this stretch of the river, about three hours east of Seattle, miners could buy that power more cheaply here than anywhere else in the nation.\n\n";

		String converted = text.stringArrayToParagraph(sentences, 2);
		Assert.assertEquals(expected, converted);
	}

	@Test
	public void convertArrayToParagraphWithInvalidMax() throws Exception {
		String[] sentences = {
		        "EAST WENATCHEE,  Washington  - Hands on the   wheel, eyes squinting against the winter sun, Lauren Miehe eases his Land Rover down the main drag and tells me how he used to spot promising sites to build a bitcoin mine, back in 2013, when he was a freshly arrived techie from Seattle and had just discovered this sleepy rural community.",
		        "Bitcoin mining - the complex process in which computers solve a complicated math puzzle to win a stack of virtual currency - uses an inordinate amount of electricity, and thanks to five hydroelectric dams that straddle this stretch of the river, about three hours east of Seattle, miners could buy that power more cheaply here than anywhere else in the nation.",
		        "Long before locals had even heard the words \"cryptocurrency\" or \"blockchain,\" Miehe and his peers realized that this semi-arid agricultural region known as the Mid-Columbia Basin was the best place to mine bitcoin in America - and maybe the world.",
		        "Outsiders are so eager to turn the basin's power into cryptocurrency that this winter, several would-be miners from Asia flew their private jet into the local airport, took a rental car to one of the local dams, and, according to a utility official, politely informed staff at the dam visitors center, \"We want to see the dam master because we want to buy some electricity.\""
		};
		String expected = "EAST WENATCHEE,  Washington  - Hands on the   wheel, eyes squinting against the winter sun, Lauren Miehe eases his Land Rover down the main drag and tells me how he used to spot promising sites to build a bitcoin mine, back in 2013, when he was a freshly arrived techie from Seattle and had just discovered this sleepy rural community.\n\n"
		        + "Bitcoin mining - the complex process in which computers solve a complicated math puzzle to win a stack of virtual currency - uses an inordinate amount of electricity, and thanks to five hydroelectric dams that straddle this stretch of the river, about three hours east of Seattle, miners could buy that power more cheaply here than anywhere else in the nation.\n\n"
		        + "Long before locals had even heard the words \"cryptocurrency\" or \"blockchain,\" Miehe and his peers realized that this semi-arid agricultural region known as the Mid-Columbia Basin was the best place to mine bitcoin in America - and maybe the world.\n\n"
		        + "Outsiders are so eager to turn the basin's power into cryptocurrency that this winter, several would-be miners from Asia flew their private jet into the local airport, took a rental car to one of the local dams, and, according to a utility official, politely informed staff at the dam visitors center, \"We want to see the dam master because we want to buy some electricity.\""
		        + "\n\n";

		String converted = text.stringArrayToParagraph(sentences, 5);
		Assert.assertEquals(expected, converted);
	}

	@Test
	public void convertArrayToParagraphWithZeroMax() {
		Assert.assertEquals("", text.stringArrayToParagraph(null, 0));
		String[] input = {};
		Assert.assertEquals("", text.stringArrayToParagraph(input, 0));
	}

	@Test
	public void testAppendParagraph() {
		String inParagraph = "EAST WENATCHEE,  Washington  - Hands on the   wheel, eyes squinting against the winter sun, Lauren Miehe eases his Land Rover down the main drag and tells me how he used to spot promising sites to build a bitcoin mine, back in 2013, when he was a freshly arrived techie from Seattle and had just discovered this sleepy rural community.\n\n"
		        + "Bitcoin mining - the complex process in which computers solve a complicated math puzzle to win a stack of virtual currency - uses an inordinate amount of electricity, and thanks to five hydroelectric dams that straddle this stretch of the river, about three hours east of Seattle, miners could buy that power more cheaply here than anywhere else in the nation.\n\n"
		        + "Long before locals had even heard the words \"cryptocurrency\" or \"blockchain,\" Miehe and his peers realized that this semi-arid agricultural region known as the Mid-Columbia Basin was the best place to mine bitcoin in America - and maybe the world.\n\n"
		        + "Outsiders are so eager to turn the basin's power into cryptocurrency that this winter, several would-be miners from Asia flew their private jet into the local airport, took a rental car to one of the local dams, and, according to a utility official, politely informed staff at the dam visitors center, \"We want to see the dam master because we want to buy some electricity.\""
		        + "\n\n";
		String outParagraph = "EAST WENATCHEE,  Washington  - Hands on the   wheel, eyes squinting against the winter sun, Lauren Miehe eases his Land Rover down the main drag and tells me how he used to spot promising sites to build a bitcoin mine, back in 2013, when he was a freshly arrived techie from Seattle and had just discovered this sleepy rural community.\n\n"
		        + "Bitcoin mining - the complex process in which computers solve a complicated math puzzle to win a stack of virtual currency - uses an inordinate amount of electricity, and thanks to five hydroelectric dams that straddle this stretch of the river, about three hours east of Seattle, miners could buy that power more cheaply here than anywhere else in the nation.\n\n"
		        + "Long before locals had even heard the words \"cryptocurrency\" or \"blockchain,\" Miehe and his peers realized that this semi-arid agricultural region known as the Mid-Columbia Basin was the best place to mine bitcoin in America - and maybe the world.\n\n"
		        + "Outsiders are so eager to turn the basin's power into cryptocurrency that this winter, several would-be miners from Asia flew their private jet into the local airport, took a rental car to one of the local dams, and, according to a utility official, politely informed staff at the dam visitors center, \"We want to see the dam master because we want to buy some electricity.\""
		        + "\n\n" + "this is the end\n\n";
		Assert.assertEquals(outParagraph, text.appendParagraph(inParagraph, "this is the end"));
	}

	@Test
	public void testAppendParagaphNullandEmpty() {
		Assert.assertEquals("", text.appendParagraph(null, "this is the end"));
		Assert.assertEquals("", text.appendParagraph("", "this is the end"));
		Assert.assertEquals("", text.appendParagraph("something", null));
		Assert.assertEquals("", text.appendParagraph("something", ""));
		Assert.assertEquals("", text.appendParagraph(null, null));
		Assert.assertEquals("", text.appendParagraph("", ""));
	}

	@Test
	public void testFixEncoding() throws IOException {
		String input = io.fileToString("src/test/resources/unit_test_files/mis-encoded.txt");
		String expected = io.fileToString("src/test/resources/unit_test_files/correctly-encoded.txt");
		String encodingMap = io.fileToString("src/test/resources/unit_test_files/encoding_map.json");
		Assert.assertEquals(expected, text.findReplace(input, encodingMap));
	}

	@Test
	public void testFindInHash1() throws Exception {
		String input = "Key1";
		String expected = "Value1";
		String hashMap = io.fileToString("src/test/resources/unit_test_files/test-search-mapping.json");
		Assert.assertEquals(expected, text.findInHash(input, hashMap));
	}

	@Test
	public void testFindInHash2() throws Exception {
		String input = "Key2";
		String expected = "value2";
		String hashMap = io.fileToString("src/test/resources/unit_test_files/test-search-mapping.json");
		Assert.assertEquals(expected, text.findInHash(input, hashMap));
	}

	@Test
	public void testFindInHash3() throws Exception {
		String input = "key3";
		String expected = "value3";
		String hashMap = io.fileToString("src/test/resources/unit_test_files/test-search-mapping.json");
		Assert.assertEquals(expected, text.findInHash(input, hashMap));
	}

	@Test
	public void testFindInHash4() throws Exception {
		String input = "Key 4";
		String expected = "Value 4";
		String hashMap = io.fileToString("src/test/resources/unit_test_files/test-search-mapping.json");
		Assert.assertEquals(expected, text.findInHash(input, hashMap));
	}

	@Test
	public void testFindInHash5() throws Exception {
		String input = " key 5";
		String expected = " value 5";
		String hashMap = io.fileToString("src/test/resources/unit_test_files/test-search-mapping.json");
		Assert.assertEquals(expected, text.findInHash(input, hashMap));
	}

	@Test
	public void testFindInHash6() throws Exception {
		String input = " key 6 ";
		String expected = " value 6 ";
		String hashMap = io.fileToString("src/test/resources/unit_test_files/test-search-mapping.json");
		Assert.assertEquals(expected, text.findInHash(input, hashMap));
	}

	@Test
	public void testFindInHashInvalidKey() throws Exception {
		String input = "invalid";
		String hashMap = io.fileToString("src/test/resources/unit_test_files/test-search-mapping.json");
		try {
			text.findInHash(input, hashMap);
		}catch (Exception e) {
			Assert.assertEquals("Key was not found", e.getMessage());
		}
	}

	@Test
	public void testFindInHashNullKey() throws Exception {
		String hashMap = io.fileToString("src/test/resources/unit_test_files/test-search-mapping.json");
		try {
			text.findInHash(null, hashMap);
		}catch (Exception e) {
			Assert.assertEquals("Input or Input Map cannot be null", e.getMessage());
		}
	}

	@Test
	public void testFindInHashNullMap() throws Exception {
		String input = "invalid";
		try {
			text.findInHash(input, null);
		}catch (Exception e) {
			Assert.assertEquals("Input or Input Map cannot be null", e.getMessage());
		}
	}

	@Test
	public void testFindInHashNullAll() throws Exception {
		try {
			text.findInHash(null, null);
		}catch (Exception e) {
			Assert.assertEquals("Input or Input Map cannot be null", e.getMessage());
		}
	}

	@Test
	public void testFindInHashEmptyInput() throws Exception {
		String input = "";
		String hashMap = io.fileToString("src/test/resources/unit_test_files/test-search-mapping.json");
		try {
			text.findInHash(input, hashMap);
		}catch (Exception e) {
			Assert.assertEquals("Input or Input Map cannot be null", e.getMessage());
		}
	}

	@Test
	public void testFindInHashEmptyInputMap() throws Exception {
		String input = "Key";
		String hashMap = "";
		try {
			text.findInHash(input, hashMap);
		}catch (Exception e) {
			Assert.assertEquals("Input or Input Map cannot be null", e.getMessage());
		}
	}
	
	@Test
	public void testSimAverage() {
		String input1 = "";
		String input2 = "";
		Double expected = 0.0;
		Double actual = text.similarAvgRate(input1, input2);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testSimAverageSlightSim1() {
		String input1 = "exclusive: pope criticises trump administration policy on migrant family separation";
		String input2 = "exclusive: pope criticizes trump administration policy on migrant family separation";
		Double expected = 1.2048192771084338;
		Double actual = text.similarAvgRate(input1, input2);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testSimAverageSlightSim2() {
		String input1 = "here's why experts are skeptical of the 'gaming disorder' diagnosis";
		String input2 = "here’s why experts are skeptical of the ‘gaming disorder’ diagnosis";
		Double expected = 4.477611940298507;
		Double actual = text.similarAvgRate(input1, input2);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testSimAverageLargeSim1() {
		String input1 = "asylum requests in eu fall from record highs, jump in u.s.: oecd";
		String input2 = "asylum requests in eu fall from record highs, jump in u.s. - oecd";
		Double expected = 3.125;
		Double actual = text.similarAvgRate(input1, input2);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testSimAverageLargeSim2() {
		String input1 = "romanian ruling party conjures parallel state fears in legal 'blitzkrieg'";
		String input2 = "insight - romanian ruling party conjures parallel state fears in legal ‘blitzkrieg’";
		Double expected = 15.384615384615385;
		Double actual = text.similarAvgRate(input1, input2);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testSimAverageLargeSim3() {
		String input1 = "123456789";
		String input2 = "987654321";
		Double expected = 88.88888888888889;
		Double actual = text.similarAvgRate(input1, input2);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testSimAverageBadInput1() {
		String input1 = null;
		String input2 = "";
		Double expected = 0.0;
		Double actual = text.similarAvgRate(input1, input2);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testSimAverageBadInput2() {
		String input1 = "";
		String input2 = null;
		Double expected = 0.0;
		Double actual = text.similarAvgRate(input1, input2);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testSimAverageBadInput3() {
		String input1 = "asylum requests in eu fall from record highs, jump in u.s.: oecd";
		String input2 = "insight - romanian ruling party conjures parallel state fears in legal ‘blitzkrieg’";
		Double expected = 89.04109589041096;
		Double actual = text.similarAvgRate(input1, input2);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testRmDuplicateChars() {
		String input = "altercations.</p></p>The monument, supremacy.</p></p></p>Police have i, and it’sw stipulates.</p></p></p></p></p>Since Monday";
		String expected = "altercations.</p>The monument, supremacy.</p>Police have i, and it’sw stipulates.</p>Since Monday";
		String actual = text.rmDupChars(input, "</p>");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testRmDuplicateCharsNoChr() {
		String input = "altercations.</p></p>The monument, supremacy.</p></p></p>Police have i, and it’sw stipulates.</p></p></p></p></p>Since Monday";
		String actual = text.rmDupChars(input, "</r>");
		Assert.assertEquals(input, actual);
	}

	@Test
	public void testRmDuplicateCharsIt() {
		String input = "altercations.</p></p>The monument, supremacy.</p></p></p>Police have i, and it’sw stipulates.</p></p></p></p></p>Since Monday";
		String expected = "altercations.</p>The monument, supremacy.</p>Police have i, and it’sw stipulates.</p>Since Monday";
		String actual = text.rmDupCharsIt(input, "</p>");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testRmDuplicateCharsNoChrIt() {
		String input = "altercations.</p></p>The monument, supremacy.</p></p></p>Police have i, and it’sw stipulates.</p></p></p></p></p>Since Monday";
		String actual = text.rmDupCharsIt(input, "</r>");
		Assert.assertEquals(input, actual);
	}

	@Test
	public void testRmDuplicateCharsRegEx() {
		String expected = "altercations.</p>The monument, supremacy.</p>Police have i, and it’sw stipulates.</p>Since Monday";
		String regex = "(<\\/p>\\s*<\\/p>)(<\\/p>\\1)*";
		String input = "altercations.</p></p>The monument, supremacy.</p></p></p>Police have i, and it’sw stipulates.</p></p></p></p>Since Monday";
		String actual = text.rmDupCharsRegEx(input, regex, "</p>");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testRmDuplicateCharsRegExSpacesInEsc() {
		String input = "The piece of paper on which Tamarion, 10, was told to write the word \\\"ma\\u0027am\\\" as a form of punishment.</p> </p>  (Teretha Wilson)</p> </p> </p> </p> </p> </p> </p> </p> </p> </p></p></p></p> </p></p> </p> </p> </p> </p> The mother of a 10-year-old boy in North Carolina is outraged that her son was recently punished for calling his fifth grade teacher \\\"ma’am.\\\"</p> </p> </p> </p> </p> </p> </p> </p> </p> </p></p> </p> </p> \\\"I was in disbelief,\\\" Teretha Wilson, the boy’s mother, told Fox News on Saturday.</p> </p> </p> </p> </p> </p> </p></p> </p> </p> Wilson noticed her son, Tamarion, was not himself when she picked him up from the bus stop earlier this week.</p> </p> </p> </p> </p> </p> </p> </p></p> </p> </p> \\\"I asked him what was wrong, and he told me he got in trouble for saying \\u0027ma’am\\u0027 to a teacher. I was confused,\\\" she said.</p> </p> </p> </p> </p> </p> </p></p> </p> </p> EX-TEACHER THREATENS \\u0027200-PLUS\\u0027 TANK ASSAULT, PROMPTING DISTRICT TO TIGHTEN SECURITY: REPORT</p> </p> </p> </p> </p> </p> </p></p> </p> </p> Inquiring further, Wilson asked her son to give more detail about the incident. That’s when the fifth-grader at North East Carolina Preparatory School in Tarboro, North Carolina, pulled out a piece of lined paper with the word ma’am written dozens of times.</p> </p> </p> </p> </p> </p> </p></p> </p> </p> </p> </p> Wilson was shocked, especially when Tamarion told her that his teacher, who has not been formally identified, told him that he was required to return the piece of paper with a parent’s signature.</p> </p> </p> </p> </p> </p> </p></p> </p> </p> The young boy also claimed that the teacher threatened to throw something at him during the incident, his mother said.</p> </p> </p> </p> </p> </p> </p></p> </p> </p> \\\"He was disappointed because he felt like he had done something wrong,\\\" she said.</p> </p> </p> </p> </p> </p> </p></p> </p> </p> The next afternoon, Wilson went to the school to meet with Tamarion’s teacher and the school\\u0027s principal. With her she brought a separate piece of paper on which her son had written the definition of ma’am. (According to the Oxford Dictionaries, ma’am is defined as \\\"a term of respectful or polite address used for a woman\\\").</p> </p> </p> </p> </p> </p> </p></p> </p> </p> Wilson claims Tamarion’s teacher told her that her son \\\"was getting on her nerve when he called her ma’am\\\" but \\\"couldn’t give me a reason of why that was bad.\\\" The teacher also claimed Tamarion knew that she wasn’t serious when she allegedly threatened to throw something at him, Wilson said.</p> </p> </p> </p> </p> </p> </p></p> </p> </p> Tamarion has been placed in a different teacher’s class since the incident occured.</p> </p> </p> </p> </p> </p> </p></p> </p> </p> In a statement to ABC 11, an official with the school called the situation a \\\"personnel matter,\\\" which \\\"has been handled appropriately by the K-7 principal.\\\"</p> </p> </p> </p> </p> </p> </p></p> </p> </p> 6TH-GRADER MIGHT RETURN TO SCHOOL AFTER FLAP OVER HAIRSTYLE</p> </p> </p> </p> </p> </p> </p></p> </p> </p> Still, Wilson said she plans to call the school board on Monday to see \\\"if they’re really going to handle it,\\\" though she did not elaborate on what this would entail.</p> </p> </p> </p> </p> </p> </p></p> </p> </p> \\\"This is about my child being respectful, then threatened to be hit with something. This isn’t about racism, as some people have made it out to be,\\\" Wilson said, referring to some of the comments on a family member’s Facebook post where she detailed the incident.</p> </p> </p> </p> </p> </p> </p></p> </p> </p> Moving forward, Wilson said she has encouraged her son to \\\"always be respectful to [his] elders even if they don’t want to be called ma’am,\\\" she said.</p> </p> </p> </p> </p> </p> </p></p> </p> </p> \\\"Is this what happens from raising your child in a good way?\\\" she asked.</p> </p> </p> </p> </p> </p> </p></p> </p> </p> A spokesperson for North East Carolina Preparatory School did not immediately respond to Fox News\\u0027 request for comment. </p> </p> </p> </p> </p> </p> </p></p> </p> </p> </p> </p> </p> </p> </p> </p> </p> </p> </p> </p> </p></p></p> </p> </p> </p> </p> </p> </p></p> </p></p>Madeline Farber is a Reporter for Fox News. You can follow her on Twitter @MaddieFarberUDK.";
		String expected = "The piece of paper on which Tamarion, 10, was told to write the word \\\"ma\\u0027am\\\" as a form of punishment.</p>  (Teretha Wilson)</p> The mother of a 10-year-old boy in North Carolina is outraged that her son was recently punished for calling his fifth grade teacher \\\"ma’am.\\\"</p> \\\"I was in disbelief,\\\" Teretha Wilson, the boy’s mother, told Fox News on Saturday.</p> Wilson noticed her son, Tamarion, was not himself when she picked him up from the bus stop earlier this week.</p> \\\"I asked him what was wrong, and he told me he got in trouble for saying \\u0027ma’am\\u0027 to a teacher. I was confused,\\\" she said.</p> EX-TEACHER THREATENS \\u0027200-PLUS\\u0027 TANK ASSAULT, PROMPTING DISTRICT TO TIGHTEN SECURITY: REPORT</p> Inquiring further, Wilson asked her son to give more detail about the incident. That’s when the fifth-grader at North East Carolina Preparatory School in Tarboro, North Carolina, pulled out a piece of lined paper with the word ma’am written dozens of times.</p> Wilson was shocked, especially when Tamarion told her that his teacher, who has not been formally identified, told him that he was required to return the piece of paper with a parent’s signature.</p> The young boy also claimed that the teacher threatened to throw something at him during the incident, his mother said.</p> \\\"He was disappointed because he felt like he had done something wrong,\\\" she said.</p> The next afternoon, Wilson went to the school to meet with Tamarion’s teacher and the school\\u0027s principal. With her she brought a separate piece of paper on which her son had written the definition of ma’am. (According to the Oxford Dictionaries, ma’am is defined as \\\"a term of respectful or polite address used for a woman\\\").</p> Wilson claims Tamarion’s teacher told her that her son \\\"was getting on her nerve when he called her ma’am\\\" but \\\"couldn’t give me a reason of why that was bad.\\\" The teacher also claimed Tamarion knew that she wasn’t serious when she allegedly threatened to throw something at him, Wilson said.</p> Tamarion has been placed in a different teacher’s class since the incident occured.</p> In a statement to ABC 11, an official with the school called the situation a \\\"personnel matter,\\\" which \\\"has been handled appropriately by the K-7 principal.\\\"</p> 6TH-GRADER MIGHT RETURN TO SCHOOL AFTER FLAP OVER HAIRSTYLE</p> Still, Wilson said she plans to call the school board on Monday to see \\\"if they’re really going to handle it,\\\" though she did not elaborate on what this would entail.</p> \\\"This is about my child being respectful, then threatened to be hit with something. This isn’t about racism, as some people have made it out to be,\\\" Wilson said, referring to some of the comments on a family member’s Facebook post where she detailed the incident.</p> Moving forward, Wilson said she has encouraged her son to \\\"always be respectful to [his] elders even if they don’t want to be called ma’am,\\\" she said.</p> \\\"Is this what happens from raising your child in a good way?\\\" she asked.</p> A spokesperson for North East Carolina Preparatory School did not immediately respond to Fox News\\u0027 request for comment. </p>Madeline Farber is a Reporter for Fox News. You can follow her on Twitter @MaddieFarberUDK.";
		String regex = "(<\\/p>\\s*<\\/p>)(<\\/p>\\1)*";
		String actual = text.rmDupCharsRegEx(input, regex, "</p>");
		Assert.assertEquals(expected, actual);
	}

}
