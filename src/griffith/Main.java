//git checkout UnitTest
package griffith;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.core.OWM.Unit;
import net.aksingh.owmjapis.model.CurrentWeather;

public class Main {
	
	public static void main(String[] args) throws APIException {
            
        // declaring object of "OWM" class with the API key
        OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
        owm.setUnit(Unit.METRIC);
        
        // getting current weather data for the chosen city
        CurrentWeather cwd = owm.currentWeatherByCityName("Berlin");
        

        //printing city name from the retrieved data
        System.out.println("City: " + cwd.getCityName());
        
        //Actual temperature
        System.out.println("Temp: "+cwd.getMainData().getTemp());
    }	
	
	public int clothesSuggestion(int temp) {
        return 0;
    }
}
