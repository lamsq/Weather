package TestBot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import griffith.Bot;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;

public class TestBot {
	
	@Test
	void testGetTemp() throws APIException { 
		//Create Bot object, with Dublin city
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm, "Dublin");
		
		double expected = 9; //expected temp
		
		assertEquals(bot.getTemp(), expected, 0.5); //evaluation of the returned data
		
	}
	
	@Test
	void testOutfitTemp() throws APIException {
		//Create Bot object, with Dublin city
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm, "Dublin");
				
		
		//Test method with different numbers
		String output = "Shorts, t-shirt, sandals";
		assertEquals(output, bot.outfitTemp());
	}
	
	@Test
	void testOutfitCloud() throws APIException {
		//Create Bot object, with Dublin city
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm, "Dublin");
		
		String expected = "headwear+sunglasses"; //expected outfit for the clouds condition
		
		assertEquals(bot.outfitCloud(), expected); //evaluation of the returned data
		
	}
	
	@Test
	void testOutfitWind() throws APIException {
		//Create Bot object, with Dublin city
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm, "Dublin");
		
		//Get wind speed by city name
	    double windSpeed = owm.currentWeatherByCityName("Dublin").getWindData().getSpeed();
	    
	    String expected = null;//Variable to store string
	    
	    //If wind speed faster than 5, expected output is "light windjacket"
	    //If wind speed solwer than 5, expected output is null
	    if(windSpeed >= 5) {
	    	expected = "light windjacket";
	    }
		
		
		assertEquals(bot.outfitWind(), expected); //evaluation of the returned data
		
	}
	
	@Test
	void testOutfitRain() throws APIException {
		//Create Bot object, with Dublin city
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm, "Dublin");
		
		String expected = "rainjacket/umbrella"; //expected outfit for the clouds condition
		
		assertEquals(bot.outfitRain(), expected); //evaluation of the returned data
		
	}
	
	@Test
	void testOutfitUV() throws APIException {
		//Create Bot object, with Dublin city
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm, "Dublin");
		
		String expected = "sunscreen"; //expected outfit for the clouds condition
		
		assertEquals(bot.outfitUV(), expected); //evaluation of the returned data
		
	}
	
	
		
}
