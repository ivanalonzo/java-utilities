package co.alonsos.java_utilities.io;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


public class IO_TextTest {

	String input = "AP\n            Published 7:34 a.m. ET Feb. 25, 2018\n        \nFILE - In this Sunday, Nov. 26, 2017 file photo Ferrari driver Sebastian Vettel of Germany waves to supporters after the Emirates Formula One Grand Prix at the Yas Marina racetrack in Abu Dhabi, United Arab Emirates. Ferrari has paid special attention to aerodynamics for its new Formula One car, the SF71H model presented in Maranello, Italy, Thursday, Feb. 22, 2018 in an attempt to keep up with rival Mercedes on high-speed circuits. (AP Photo/Luca Bruno, file)(Photo: The Associated Press)\n\n\nMADRID (AP) — Formula One teams will finally take their 2018 cars to the track when preseason testing begins Monday in Spain, with Mercedes and Ferrari promising significant improvements to try to keep running up front.\nThis week's test session in Barcelona will also allow McLaren to debut its Renault engine after three dismal years with Honda, and give Red Bull a chance to show it has a car fast enough to finally contend for titles again.\nForce India and Williams will look to move closer to the top three, while Renault and Hass will try to keep improving. Toro Rosso will be the team trying to succeed with the Honda engine, and Sauber will bring partner Alfa Romeo back to F1 hoping to take a step forward after a last-place finish a year ago.\nWhat all teams will have is the halo, the protective cockpit device that is mandatory this season. The odd-looking cover that goes around the drivers' heads is the most visible change to cars from a year ago. The \"shark fins\" and their adjacent tiny wings on top of the cars are gone this season, and each driver will only be allowed to use three power units — instead of four — during the 21-race calendar.\nThe season opens on March 25 in Australia. Drivers will participate in another four-day test session in Barcelona from March 6-9. Most teams unveiled their cars last week, when drivers were allowed to get a first taste of action in limited outings for video filming.\nMercedes dominated the last four seasons but Lewis Hamilton faced a tough challenge from Ferrari's Sebastian Vettel a year ago.\n\"Last year the car was fantastic, but there were so many things we could still improve on,\" Hamilton said. \"What we've done is taken a lot of the DNA from last year's car... this is the sister car, an evolution of that. This is better than last year's car in every aspect. It looks quite similar but inside, underneath the shell, and even the bodywork, it's all refined to perform even better than it did last year.\"\nFerrari won five races with Vettel last season, but the Italian team hasn't won a drivers' title since 2007 with Kimi Raikkonen. Its last constructors' championship came in 2008.\n\"We can't wait to get out on to the track to see how the car performs, how it behaves,\" Vettel said. \"This car is a big step from last year.\"\nRed Bull, which won four straight titles with Vettel from 2010-13, was the third force last season and at times it got extremely close to Mercedes and Ferrari. Max Verstappen won two races and Daniel Ricciardo one. Red Bull will debut a new suspension — in addition to new livery — that it hopes will allow the team to make a leap forward and become a title contender.\nMcLaren is likely the team most anxious to get to the track after swapping from Honda to Renault, a move it hopes will end the struggles from the last few seasons, when it dealt with reliability issues and lack of power.\n\"I think good times are coming,\" two-time world champion Fernando Alonso told Sky Sports. \"We struggled, we kept motivation very high. I think the team is stronger now than three years ago. What doesn't kill you makes you stronger ... we will see the first results this year.\"\nThere will be two rookie drivers on the grid: Russian Sergey Sirotkin with Williams and Monaco-born Charles Leclerc with Sauber, which is actually calling itself Alfa Romeo Sauber F1 Team after inking a technical and commercial partnership with the Italian brand that has a long F1 history.\nPolish driver Robert Kubica, who hasn't raced in F1 since 2010 after being badly injured in a rally crash, signed as a reserve driver with Williams and is expected to participate in three practice sessions this season.\n\"We won the championship last year but it was difficult at times, some really strong opposition,\" Mercedes chief Toto Wolff said. \"We needed to find the right balance between developing our car without losing its raw speed. Last year's car was the fastest car on track — it won the most races and had the most pole positions. We needed to preserve that.\"\n___\nTales Azzoni on Twitter:  2018 The Associated Press. All rights reserved. This material may not be published, broadcast, rewritten or redistributed.";
	String expected = "AP<br><br><br><br><br><br><br><br>Published 7:34 a.m. ET Feb. 25, 2018<br><br><br><br><br><br><br><br>FILE - In this Sunday, Nov. 26, 2017 file photo Ferrari driver Sebastian Vettel of Germany waves to supporters after the Emirates Formula One Grand Prix at the Yas Marina racetrack in Abu Dhabi, United Arab Emirates. Ferrari has paid special attention to aerodynamics for its new Formula One car, the SF71H model presented in Maranello, Italy, Thursday, Feb. 22, 2018 in an attempt to keep up with rival Mercedes on high-speed circuits. (AP Photo/Luca Bruno, file)(Photo: The Associated Press)<br><br><br><br><br><br>MADRID (AP) — Formula One teams will finally take their 2018 cars to the track when preseason testing begins Monday in Spain, with Mercedes and Ferrari promising significant improvements to try to keep running up front.<br><br>This week's test session in Barcelona will also allow McLaren to debut its Renault engine after three dismal years with Honda, and give Red Bull a chance to show it has a car fast enough to finally contend for titles again.<br><br>Force India and Williams will look to move closer to the top three, while Renault and Hass will try to keep improving. Toro Rosso will be the team trying to succeed with the Honda engine, and Sauber will bring partner Alfa Romeo back to F1 hoping to take a step forward after a last-place finish a year ago.<br><br>What all teams will have is the halo, the protective cockpit device that is mandatory this season. The odd-looking cover that goes around the drivers' heads is the most visible change to cars from a year ago. The \"shark fins\" and their adjacent tiny wings on top of the cars are gone this season, and each driver will only be allowed to use three power units — instead of four — during the 21-race calendar.<br><br>The season opens on March 25 in Australia. Drivers will participate in another four-day test session in Barcelona from March 6-9. Most teams unveiled their cars last week, when drivers were allowed to get a first taste of action in limited outings for video filming.<br><br>Mercedes dominated the last four seasons but Lewis Hamilton faced a tough challenge from Ferrari's Sebastian Vettel a year ago.<br><br>\"Last year the car was fantastic, but there were so many things we could still improve on,\" Hamilton said. \"What we've done is taken a lot of the DNA from last year's car... this is the sister car, an evolution of that. This is better than last year's car in every aspect. It looks quite similar but inside, underneath the shell, and even the bodywork, it's all refined to perform even better than it did last year.\"<br><br>Ferrari won five races with Vettel last season, but the Italian team hasn't won a drivers' title since 2007 with Kimi Raikkonen. Its last constructors' championship came in 2008.<br><br>\"We can't wait to get out on to the track to see how the car performs, how it behaves,\" Vettel said. \"This car is a big step from last year.\"<br><br>Red Bull, which won four straight titles with Vettel from 2010-13, was the third force last season and at times it got extremely close to Mercedes and Ferrari. Max Verstappen won two races and Daniel Ricciardo one. Red Bull will debut a new suspension — in addition to new livery — that it hopes will allow the team to make a leap forward and become a title contender.<br><br>McLaren is likely the team most anxious to get to the track after swapping from Honda to Renault, a move it hopes will end the struggles from the last few seasons, when it dealt with reliability issues and lack of power.<br><br>\"I think good times are coming,\" two-time world champion Fernando Alonso told Sky Sports. \"We struggled, we kept motivation very high. I think the team is stronger now than three years ago. What doesn't kill you makes you stronger ... we will see the first results this year.\"<br><br>There will be two rookie drivers on the grid: Russian Sergey Sirotkin with Williams and Monaco-born Charles Leclerc with Sauber, which is actually calling itself Alfa Romeo Sauber F1 Team after inking a technical and commercial partnership with the Italian brand that has a long F1 history.<br><br>Polish driver Robert Kubica, who hasn't raced in F1 since 2010 after being badly injured in a rally crash, signed as a reserve driver with Williams and is expected to participate in three practice sessions this season.<br><br>\"We won the championship last year but it was difficult at times, some really strong opposition,\" Mercedes chief Toto Wolff said. \"We needed to find the right balance between developing our car without losing its raw speed. Last year's car was the fastest car on track — it won the most races and had the most pole positions. We needed to preserve that.\"<br><br>___<br><br>Tales Azzoni on Twitter:<br>2018 The Associated Press. All rights reserved. This material may not be published, broadcast, rewritten or redistributed.";
	@Test
	public void testConvertToHTML() throws Exception {

		IO_Text text = new IO_Text();
		String converted = text.convertToHTML(input);
		Assert.assertEquals(expected, converted);
		
		//System.out.println(converted);
	}

}
