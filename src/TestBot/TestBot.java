package TestBot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import griffith.Bot;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;

public class TestBot {
	@Test
	void testOutfitTemp() throws APIException {
		//Create Bot object, with Dublin city
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm, "Dublin");
				
		
		//Test method with different numbers
		String output = "Shorts+t-shirt+sandals";
		assertEquals(output, bot.outfitTemp());
	}
	
	@Test
	void testGetTemp() throws APIException {
		//Create Bot object, with Dublin city
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm, "Dublin");
		
		double expected = 0; //expected temp
		
		assertEquals(bot.getTemp(), expected, 0.5); //evaluation of the returned data
		
	}
		
}
