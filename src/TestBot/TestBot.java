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
		String output1 = bot.outfitTemp();
		assertEquals("Jacket+sweatshirt/hoodie/sweater+pants+footwear", output1);
		
		String output2 = bot.outfitTemp();
		assertEquals("Pants/shorts+shirt/t-shirt+footwear", output2);
		
		//Test method with negative number
		String output3 = bot.outfitTemp();
		assertEquals("Down jacket+sweatshirt/hoodie/sweater+hat+gloves+boots+pants", output3);
		
		//Test method with extreme negative number
		String output4 = bot.outfitTemp();
		assertEquals("Thick down jacket+sweatshirt/hoodie/sweater+winter hat+gloves+boots+insulated pants", output4);
		
		//Test method with decimal number
		String output5 = bot.outfitTemp();
		assertEquals("Sweatshirt/hoodie/sweater+pants+footwear", output5);
	
		String output6 = bot.outfitTemp();
		assertEquals("Down jacket+sweatshirt/hoodie/sweater+winter hat+gloves+boots+insulated pants", output6);

		String output7 = bot.outfitTemp();
		assertEquals("Shorts+t-shirt+sandals", output7);
		
		
	}
}
