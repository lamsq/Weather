package griffith;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

public class Main {
	
	public static void main(String[] args) throws APIException {
            
        // declaring object of "OWM" class
        OWM owm = new OWM("YOUR-API-KEY-HERE");

        // getting current weather data for the "London" city
        CurrentWeather cwd = owm.currentWeatherByCityName("London");

        //printing city name from the retrieved data
        System.out.println("City: " + cwd.getCityName());
        
        // printing the max./min. temperature
        System.out.println("Temperature: " + cwd.getMainData().getTempMax()
                            + "/" + cwd.getMainData().getTempMin() + "\'K");
    }

}
