package griffith;

import java.util.List;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.DailyUVIndexForecast;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import net.aksingh.owmjapis.model.param.Cloud;
import net.aksingh.owmjapis.model.param.Rain;
import net.aksingh.owmjapis.model.param.Wind;

public class Bot {
	//Attributes
	private OWM owm;
	private CurrentWeather cwd;
	private HourlyWeatherForecast wf;
	
	//constructor
	public Bot(OWM owm, String city) throws APIException {
		this.owm = owm; //openweathermap object that contains API key
		cwd = owm.currentWeatherByCityName(city); //current weather object by city name
		wf = owm.hourlyWeatherForecastByCityName(city);  //hourly weather forecast object by city name	
		
	}

	//Getter
	public double getTemp() {
		return 0.6;
	}
	
	//outfit suggestions method (according to the temperature)
	public String outfitTemp() {
        return null;
    }
	
	//outfit suggestions method (according to the cloudiness)
	public String outfitCloud() {
        return null;
    }
	
	//outfit suggestions method (according to the wind)
	public String outfitWind() {
        return null;
    }
	
	//outfit suggestions method (according to the rain)
	public String outfitRain() {
        return null;
    }
	
	//outfit suggestions method (according to the UV index)
	public String outfitUV() {
        return null;
    }
	
	
	
}
