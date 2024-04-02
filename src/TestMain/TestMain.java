package TestMain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import griffith.Bot;
import griffith.Main;

class TestMain {

	@Test
	void testClothesSuggestion() {
		//Create Bot object with initialize temp as 0
		Bot bot = new Bot(0);
		
		//Test method with different numbers
		String output = bot.clothesSuggestion(10);
		assertEquals("Wear jacket", output);
		
		String output2 = bot.clothesSuggestion(26);
		assertEquals("Wear t-shirt", output2);
		
		//Test method with negative number
		String output3 = bot.clothesSuggestion(-5);
		assertEquals("Wear jacket and sweater", output3);
		
		//Test method with extreme negative number
		String output4 = bot.clothesSuggestion(-50);
		assertEquals("Too cold, don't go outside", output4);
		
		//Test method with decimal number
		String output5 = bot.clothesSuggestion(14.55);
		assertEquals("Wear hoodie", output5);
		
		//Test method with extreme positive number
		String output6 = bot.clothesSuggestion(80);
		assertEquals("Too hot, don't go outside", output6);

		
		
	}

}
