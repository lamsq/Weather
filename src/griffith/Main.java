//git checkout UnitTest
package griffith;

import java.util.Scanner;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.core.OWM.Unit;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import net.aksingh.owmjapis.model.param.Coord;

public class Main {
	
	public static void main(String[] args) throws APIException {
		
        OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612"); // declaring object of "OWM" class with the API key
        owm.setUnit(OWM.Unit.METRIC); //sets units to metric
        owm.setAccuracy(OWM.Accuracy.ACCURATE); //sets accuracy
    
	    Scanner input = new Scanner(System.in); //scanner object for user input
	    
	    Bot bot = new Bot(owm); //creates the bot object
	    
	    //greeting message
	    String greeting = "Welcome to the SuperWeather chatbot!\nThis bot can help you to decide what to wear!\nWhat is your name?";
	    String name = "\nMy name is: ";
	    System.out.println(greeting.concat(name)); 
	    
	    name = input.nextLine(); //reassigns the variable value with the user name
	    
	    //sets the intro message so the user knows how to interact with the bot and prints the message
	    String intro = "\nNice to meet you, ".concat(name).concat("."
	    		+ "\nI can help you to choose the appropriate outfir for the current weather or for the upcoming days!"
	    		+ "\nJust send me the city name for the current weather or where and when you want to go!\n"); 
	    System.out.println(intro);
	    
	    boolean loop = true; //flag for loop condition
	    
	    //main loop to process user inputs
	    while (loop) {
	    	
	    	String choice = input.nextLine();
	    	
	    	
	    	
      
      
      
	    }
       
    }	

}
