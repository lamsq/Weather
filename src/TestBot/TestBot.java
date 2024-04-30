package TestBot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.Test;

import griffith.Bot;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.*;

public class TestBot {
	
	@Test
	void testGetTemp() throws APIException { 
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
		double expected = 7; //expected temperature
		
		assertEquals(bot.getTemp("Berlin"), expected, 2); //evaluation of the returned data
	}
	
	@Test
	void testOutfitTemp() throws APIException {
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
		//Test method with different numbers
		String output = "Jacket, sweatshirt/hoodie/sweater, pants, footwear";
		assertEquals(output, bot.outfitTemp("Berlin"));
	}
	
	@Test
	void testOutfitCloud() throws APIException {
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
		String expected = "; optional/no headwear"; //expected outfit for the clouds condition
		
		assertEquals(bot.outfitCloud("days"), expected); //evaluation of the returned data
	}
	
	@Test
	void testOutfitWind() throws APIException {
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
	    String expected = "; light windjacket";//Variable to store string	
	    
		assertEquals(bot.outfitWind("Days"), expected); //evaluation of the returned data
	}
	
	@Test
	void testOutfitRain() throws APIException {
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
		String expected = ""; //expected outfit for the clouds condition
		
		assertEquals(bot.outfitRain("Berlin"), expected); //evaluation of the returned data
	}
	
	@Test
	void testOutfitUV() throws APIException {
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
		String expected = ""; //expected value
		
		assertEquals(bot.outfitUV("Moscow"), expected);//evaluation of the returned data
	}
	
	@Test
	void testForecastDate() throws APIException {
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
		LocalDate expected = LocalDate.of(2024, 04, 17); //expected value
		
		assertEquals(bot.forecastDate(1)[0], expected);//evaluation of the returned data
	}
	
	@Test
	void testIsCityName() throws APIException {
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
		//Test with valid input, expected true
		assertEquals(bot.isCityName("Dublin"), true);
		
	}
	
	@Test
	void testInputProcessing() throws APIException {
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
		String expected = "Dublin"; //expected value

		String actual = bot.inputProcessing("Dublin next 3 days").get("city").get(0); //actual value that is returned by the method
		
		//Test with valid input, expected true
		assertEquals(actual, expected);
		
	}
		
	
	
	
	@Test
	void testOutfitTempForecast() throws APIException {
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
		//Test method with different numbers
		String output = "Jacket, sweatshirt/hoodie/sweater, pants, footwear";
		assertEquals(output, bot.outfitTempForecast("Berlin", null));
	}
	
	@Test
	void testOutfitCloudForecast() throws APIException {
		//Create Bot object with stated API key
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
		String expected = "; optional/no headwear"; //expected outfit for the clouds condition
		
		assertEquals(bot.outfitCloudForecast("days", null), expected); //evaluation of the returned data
	}
	
	
	
	
}
