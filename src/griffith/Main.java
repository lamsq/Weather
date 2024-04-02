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
	
	public String clothesSuggestion(double temp) {
		if(temp < -10){
            return "Too cold, don't go outside";
        }else if(temp <= 0 && temp > -10){
            return "Wear jacket and sweater";
        }else if(temp <= 10 && temp > 0){
            return "Wear jacket";
        }else if(temp <= 20 && temp > 10){
            return "Wear hoodie";
        }else if(temp <= 30 && temp > 20){
            return "Wear t-shirt";
        }else if(temp > 30){
            return "Too hot, don't go outside";
        }
        	
        return null;
    }
}
