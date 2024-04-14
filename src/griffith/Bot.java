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

	//Getter for the current temperature
	public double getTemp() {
		return cwd.getMainData().getTemp(); //returns the value
	}
	
	//outfit suggestions method (according to the temperature)
	public String outfitTemp() {
		
		double temp = this.getTemp();
		String result;
		
		if (temp <-20) {
			result = "Thick down jacket, sweatshirt/hoodie/sweater, winter hat, gloves, boots, insulated pants";
		}
		else if(temp>=-20 && temp<-10) {
			result ="Down jacket, sweatshirt/hoodie/sweater, winter hat, gloves, boots, insulated pants";
		}
		else if(temp>=-10 && temp<0) {
			result = "Down jacket, sweatshirt/hoodie/sweater+hat, gloves, boots, pants";
		}
		else if (temp>=0 && temp<10) {
			result = "Jacket, sweatshirt/hoodie/sweater, pants, footwear";
		}
		else if (temp>=10 && temp<15) {
			result = "Sweatshirt/hoodie/sweater, pants, footwear";
		}
		else if (temp>=15 && temp<20) {
			result = "Pants/shorts, shirt/t-shirt, footwear";
		}
		else if (temp>=20) {
			result = "Shorts, t-shirt, sandals";
		}
		else {
			result = "Something went wrong, try again later";
		}
        return result;
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
