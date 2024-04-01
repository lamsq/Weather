package griffith;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

public class Main {
	
	public static void main(String[] args) throws APIException {
            
        // declaring object of "OWM" class with the API key
        OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");

        // getting current weather data for the chosen city
        CurrentWeather cwd = owm.currentWeatherByCityName("Dublin");

        //printing city name from the retrieved data
        System.out.println("City: " + cwd.getCityName());
        
        // printing the max./min. temperature
        System.out.println("Temperature: " + cwd.getMainData().getTempMax() + "/" + cwd.getMainData().getTempMin() + "\'K");
        
        System.out.println(cwd.getMainData().getTemp());
        System.out.println(cwd.getMainData().getTempKf());
        System.out.println(cwd.getMainData().getSeaLevel());
        
    }
	
	//converts the temperature from K to C	
	public static double kelvinToCelcius(double kelvin) { 
		return 99; //returns the converted value
	}
	
	

}
