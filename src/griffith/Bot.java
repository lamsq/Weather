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
	//Attribute
	private OWM owm;
	private double temp;
	private Cloud cloud;
	private Wind wind;
	private Rain rain;
	private List<DailyUVIndexForecast> uv;
	
	
	
	//constructor
	public Bot(OWM owm) throws APIException {
		this.owm = owm;
		CurrentWeather cwd = owm.currentWeatherByCityName("Berlin");
		this.temp = cwd.getMainData().getTemp();
		this.cloud = cwd.getCloudData();
		this.wind = cwd.getWindData();
		this.rain = cwd.getRainData();
		this.uv = owm.dailyUVIndexForecastByCoords(0, 0);
		
	}

	//Getter and Setter
	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}
	
	
	public String outfitTemp(double temp) {
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
