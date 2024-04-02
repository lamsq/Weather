package TestMain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import griffith.Main;

class TestMain {

	@Test
	void testClothesSuggestion() {
		Main main = new Main();
		
		String output = main.clothesSuggestion(10);
		assertEquals("Wear jacket", output);
		
		String output2 = main.clothesSuggestion(26);
		assertEquals("Wear t-shirt", output2);
		
		String output3 = main.clothesSuggestion(-5);
		assertEquals("Wear jacket and sweater", output3);
	}

}
