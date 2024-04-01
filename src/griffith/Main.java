package griffith;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

public class Main {
	
	public static void main(String[] args) throws APIException {
            
        // declaring object of "OWM" class with the API key
        OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");

        // getting current weather data for the chosen city
        CurrentWeather cwd = owm.currentWeatherByCityName("Berlin");

        //printing city name from the retrieved data
        System.out.println("City: " + cwd.getCityName());
        
        // printing the max./min. temperature
        System.out.println("Temperature: " + cwd.getMainData().getTempMax() + "/" + cwd.getMainData().getTempMin() + "\'K");
        
    }
	
	//converts the temperature from K to C	
	public static double kelvinToCelcius(double kelvin) { 
		return Math.round((kelvin-273.15)*100)/100; //returns the converted value rounded to 2 decimals
	}
	
	

}
