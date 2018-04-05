package co.alonsos.java_utilities.io;

import java.util.HashMap;
import java.util.StringTokenizer;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IO_TextTest {
	IO_Text text;

	@BeforeEach
	public void setup() {
		text = new IO_Text();
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
}
