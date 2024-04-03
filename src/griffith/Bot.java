package griffith;

import java.util.List;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.DailyUVIndexForecast;
import net.aksingh.owmjapis.model.param.Cloud;
import net.aksingh.owmjapis.model.param.Rain;
import net.aksingh.owmjapis.model.param.Wind;

public class Bot {
	//Attributes
	private OWM owm;
	private double temp;
	private Cloud cloud;
	private Wind wind;
	private Rain rain;
	private List<DailyUVIndexForecast> uv;
	
	//constructor
	public Bot(OWM owm, String city) throws APIException {
		this.owm = owm;
		CurrentWeather cwd = owm.currentWeatherByCityName(city); //current weather object by city name
		//assigns the attributes values by calling the owm methods
		this.temp = cwd.getMainData().getTemp();
		this.cloud = cwd.getCloudData();
		this.wind = cwd.getWindData();
		this.rain = cwd.getRainData();
		//gets city coordinates
		double cityLat = cwd.component4().getLatitude();
		double cityLon = cwd.component4().getLongitude();
		//assigns uv index forecast using city coordinates
		this.uv = owm.dailyUVIndexForecastByCoords(cityLat, cityLon);
		
	}

	//Getter
	public double getTemp() {
		return 0;
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
