package TestMain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import griffith.Main;

class TestMain {

	@Test
	void testClothesSuggestion() {
		Main main = new Main();
		
		//Test method with different numbers
		String output = main.clothesSuggestion(10);
		assertEquals("Wear jacket", output);
		
		String output2 = main.clothesSuggestion(26);
		assertEquals("Wear t-shirt", output2);
		
		//Test method with negative number
		String output3 = main.clothesSuggestion(-5);
		assertEquals("Wear jacket and sweater", output3);
		
		//Test method with extreme negative number
		String output4 = main.clothesSuggestion(-50);
		assertEquals("Too cold, don't go outside", output4);
		
		//Test method with decimal number
		String output5 = main.clothesSuggestion(14.55);
		assertEquals("Wear hoodie", output5);
		
		//Test method with extreme positive number
		String output6 = main.clothesSuggestion(80);
		assertEquals("Too hot, don't go outside", output6);

		
		
	}

}
