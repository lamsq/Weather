//git checkout UnitTest
package griffith;

import java.util.Scanner;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.core.OWM.Unit;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.param.Coord;

public class Main {
	
	public static void main(String[] args) throws APIException {
		
		// declaring object of "OWM" class with the API key
        OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
        owm.setUnit(OWM.Unit.METRIC); //sets units to metric
        owm.setAccuracy(OWM.Accuracy.ACCURATE); //sets accuracy
    
	    Scanner input = new Scanner(System.in); //scanner object
	    
	    Bot bot = new Bot(owm, "Dublin"); //creates the bot object
	    
	    //greeting message
	    String greeting = "Welcome to the SuperWeather chatbot!\nThis bot can help you to decide what to wear!\nWhat is your name?";
	    String name = "\nMy name is: ";
	    System.out.println(greeting.concat(name)); 
	    
	    name = input.nextLine(); //reassigns the user name
	    
	    String intro = "\nNice to meet you, ".concat(name).concat(". Where are you now?\nI am in: "); 
	    System.out.println(intro);
	    
	    boolean loop = true; //flag for loop condition
	    
	    //main loop to process user inputs
	    while (loop) {
      
	    	String city = input.nextLine(); //assigns the user input 
      
      
      
	    }
       
    }	

}
