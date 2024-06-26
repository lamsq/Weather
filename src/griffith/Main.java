//git checkout UnitTest
package griffith;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
	    
	    HourlyWeatherForecast wfd = owm.hourlyWeatherForecastByCityName("Berlin");

	    //main loop to process user inputs
	    while (loop) {
	    	//Store user input
	    	String choice = input.nextLine();	    	
	    	HashMap<String, ArrayList<String>> data = bot.inputProcessing(choice); //sorted user data
	    	
	    	if(data!=null) {
	    		
	    		if (data.get("mode").get(0).equals("current weather")) {
		    		for (int i=0; i<data.get("city").size(); i++) { //loop through the cities
			    		System.out.println(bot.outfitCurrentWeather(data.get("city").get(i))+"\n"); //prints the appropriate outfit
			    	}		    		
		    	} 
	    		
	    		else if (data.get("mode").get(0).equals("single forecast")) {	//condition for single forecast		    		
		    		for (int i=0; i<data.get("city").size(); i++) {	//loop through the cities		     			
		    			LocalDate start = LocalDate.parse(data.get("start local dates").get(i)); //forecast date for each city	    			
		    			LocalDate[] dates = new LocalDate[]{start};	//array with date to pass it to the method	    			
		    			System.out.println(bot.outfitForecastWeather(data.get("city").get(i), dates));	//calls forecast method	    			
		    		}	    		
		    	} 
		    	
		    	else if (data.get("mode").get(0).equals("period forecast")){	//condition for period forecast	    		
		    		for (int i=0; i<data.get("city").size(); i++) {	//loop through the cities	
		    			//gets start and end dates for the forecast
		    			LocalDate start = LocalDate.parse(data.get("start local dates").get(i)); 
		    			LocalDate end = LocalDate.parse(data.get("end local dates").get(i));
		    			LocalDate[] dates = new LocalDate[]{start, end}; //adds dates to the array		    			
		    			System.out.println(bot.outfitForecastWeather(data.get("city").get(i), dates));	//calls the forecast method with chosen dates    			
		    		}		    		
		    	} 
		    	
		    	else if (data.get("mode").get(0).equals("no city")) {		    		
		    		
		    	} 
		    	
		    	else if (data.get("mode").get(0).equals("stop")){
		    		loop = false; //toggles the flag
		    		break; //breaks the loop
		    	}
	    		
		    	else if (data.get("mode").get(0).equals("future")){
		    		continue; //another iteration
		    	}	
	    		
		    	else {		    		
		    		System.out.println("Too much data, can't figure out what you need :(\nTry again;");	//prints the message	    		
		    	}	    		
	    	}
	    	else {
	    		continue; //another iteration
	    	}
	    	
	    }
       
    }	

}
