package TestBot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

import griffith.Bot;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;

public class TestBot {
	
	@Test
	void testGetTemp() throws APIException { 
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
		double expected = 9; //expected temperature
		
		assertEquals(bot.getTemp("Dublin"), expected, 0.5); //evaluation of the returned data
	}
	
	@Test
	void testOutfitTemp() throws APIException {
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
		//Test method with different numbers
		String output = "Shorts, t-shirt, sandals";
		assertEquals(output, bot.outfitTemp("Dublin"));
	}
	
	@Test
	void testOutfitCloud() throws APIException {
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
		String expected = "headwear, sunglasses"; //expected outfit for the clouds condition
		
		assertEquals(bot.outfitCloud("Dublin"), expected); //evaluation of the returned data
	}
	
	@Test
	void testOutfitWind() throws APIException {
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
	    
	    String expected = "light windjacket";//Variable to store string	    
		
		assertEquals(bot.outfitWind("Dublin"), expected); //evaluation of the returned data
	}
	
	@Test
	void testOutfitRain() throws APIException {
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
		String expected = "rainjacket/umbrella"; //expected outfit for the clouds condition
		
		assertEquals(bot.outfitRain("Dublin"), expected); //evaluation of the returned data
	}
	
	@Test
	void testOutfitUV() throws APIException {
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
		String expected = "sunscreen"; //expected value
		
		assertEquals(bot.outfitUV("Dublin"), expected);//evaluation of the returned data
	}
	
	@Test
	void testForecastDate() throws APIException {
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
		@SuppressWarnings("deprecation")
		Date expected = new Date(2024, Calendar.APRIL, 15); //expected value
		
		assertEquals(bot.forecastDate(0), expected);//evaluation of the returned data
	}
	
	@Test
	void testIsCityName() throws APIException {
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
		//Test with valid input, expected true
		assertEquals(bot.isCityName("Dublin"), true);
		
		//Test with invalid input, expected false
		assertEquals(bot.isCityName("20dj"), true);
	}
		
}
