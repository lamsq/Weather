package TestBot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import griffith.Bot;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;

public class TestBot {
	@Test
	void testOutfitTemp() throws APIException {
		//Create Bot object with initialize temp as 0
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		Bot bot = new Bot(owm);
		
		//Test method with different numbers
		String output = bot.outfitTemp(10);
		assertEquals("Jacket+sweatshirt/hoodie/sweater+pants+footwear", output);
		
		String output2 = bot.outfitTemp(18);
		assertEquals("Pants/shorts+shirt/t-shirt+footwear", output2);
		
		//Test method with negative number
		String output3 = bot.outfitTemp(-5);
		assertEquals("Down jacket+sweatshirt/hoodie/sweater+hat+gloves+boots+pants", output3);
		
		//Test method with extreme negative number
		String output4 = bot.outfitTemp(-50);
		assertEquals("Thick down jacket+sweatshirt/hoodie/sweater+winter hat+gloves+boots+insulated pants", output4);
		
		//Test method with decimal number
		String output5 = bot.outfitTemp(14.55);
		assertEquals("Sweatshirt/hoodie/sweater+pants+footwear", output5);
	
		String output6 = bot.outfitTemp(-15.2);
		assertEquals("Down jacket+sweatshirt/hoodie/sweater+winter hat+gloves+boots+insulated pants", output6);

		String output7 = bot.outfitTemp(30.1);
		assertEquals("Shorts+t-shirt+sandals", output7);
		
		
	}
}
