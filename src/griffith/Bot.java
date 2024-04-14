package griffith;

import java.util.*;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.*;



public class Bot {
	//Attributes
	private OWM owm; //openweathermap object
	private CurrentWeather cwd; //current weather object
	private HourlyWeatherForecast wfd; //weather forecast object
	
	//constructor
	public Bot(OWM owm, String city) throws APIException {
		this.owm = owm; //openweathermap object that contains API key
		
		wfd = owm.hourlyWeatherForecastByCityName(city);  //hourly weather forecast object by city name	
		
	}

	//Getter for the current temperature
	public double getTemp(String city) throws APIException{
		cwd = owm.currentWeatherByCityName(city); //current weather object by city name
		return cwd.getMainData().getTemp(); //returns the value
	}
	
	//outfit suggestions method (according to the temperature)
	public String outfitTemp(String city) throws APIException{
		cwd = owm.currentWeatherByCityName(city); //current weather object by city name
		double temp = this.getTemp(city); //gets current temperature in the chosen city
		String result = ""; //creates the variable for return statement
		
		if (temp <-20) //outfit for temperature below -20 degrees
			result = "Thick down jacket, sweatshirt/hoodie/sweater, winter hat, gloves, boots, insulated pants";
		else if(temp>=-20 && temp<-10)  //outfit for temperature between -20 and -10 degrees
			result ="Down jacket, sweatshirt/hoodie/sweater, winter hat, gloves, boots, insulated pants";
		else if(temp>=-10 && temp<0)  //outfit for temperature between -10 and 0 degrees
			result = "Down jacket, sweatshirt/hoodie/sweater, hat, gloves, boots, pants";
		else if (temp>=0 && temp<10)  //outfit for temperature between 0 and 10 degrees
			result = "Jacket, sweatshirt/hoodie/sweater, pants, footwear";
		else if (temp>=10 && temp<15)  //outfit for temperature between 10 and 15 degrees
			result = "Sweatshirt/hoodie/sweater, pants, footwear";
		else if (temp>=15 && temp<20)  //outfit for temperature between 15 and 20 degrees
			result = "Pants/shorts, shirt/t-shirt, footwear";
		else if (temp>=20)  //outfit for temperature above 20 degrees
			result = "Shorts, t-shirt, sandals";
		else //for other outcomes
			result = "Something went wrong, try again later";
        return result; //returns the result
    }
	
	//outfit suggestions method (according to the cloudiness)
	public String outfitCloud(String city) throws APIException{
		cwd = owm.currentWeatherByCityName(city); //current weather object by city name
		String result= ""; //creates the variable for return statement
		if(cwd.hasCloudData()) { //if there's cloud data
			double temp = this.getTemp(city); //gets current temperature in the chosen city
			double cloud = cwd.getCloudData().component1(); //gets current cloud data in the chosen city
			if (cloud>80 && temp>=0) //if clouds more than 80% and temperature equals or above 0
				result = "optional/no headwear";
			else if (cloud<20 && temp>=0)  //if clouds less than 20% and temperature equals or above 0
				result = "headwear, sunglasses";
			else if (cloud<20 && temp>15)  //if clouds less than 20% and temperature above 15
				result = "light headwear, sunglasses";
		}
		return result; //returns the result
    }
 	
	//outfit suggestions method (according to the wind)
	public String outfitWind(String city) throws APIException {
		cwd = owm.currentWeatherByCityName(city); //current weather object by city name
		String result = ""; //Variable to store output message
	    if(cwd.hasWindData()) { //if there's wind data
	    	double wind = cwd.getWindData().getSpeed(); //Get wind speed
	    	if(wind>=3.4 && wind<5.4) //conditions for the light wind (speed from 3.4 to 5.4 m/s)
		    	result = "light windjacket";
		    else if(wind>=5.4 && wind<7.9) //conditions for the moderate wind (speed from 5.4 to 7.9 m/s)
		    	result = "windjacket";
		    else if(wind>=7.9) //conditions for the strong wind (speed from 7.9 m/s)
		    	result = "windjacket, fleece/sweatshirt";
	    }
        return result; //returns the result
    }
	
	//outfit suggestions method (according to the rain)
	public String outfitRain(String city) throws APIException {
		cwd = owm.currentWeatherByCityName(city); //current weather object by city name
		String result = ""; //Variable to store output message
		if (cwd.hasRainData()) {
			double rain = cwd.getRainData().getPrecipVol3h(); //Get wind speed
		    if(rain>0) {
		    	result = "rainjacket/umbrella";
		    }
		}
        return result; //returns result
    }
	
	//outfit suggestions method (according to the UV index)
	public String outfitUV(String city) throws APIException {
		cwd = owm.currentWeatherByCityName(city); //current weather object by city name
		double lat, lon; //initialize variables for city coordinates
		String result =""; //result variable
		
		if(cwd.hasCoordData()) { //condition to check if uv data exists
			lat = cwd.getCoordData().getLatitude(); //assigns the latitude
			lon = cwd.getCoordData().getLongitude(); //assigns the longitude
			double uv = owm.currentUVIndexByCoords(lat, lon).getValue(); //assigns the uv index value
			if(uv>3) //condition if uv is higher than 3
				result = "Sunscreen"; //offers to use sunscreen
		}
        return result; //returns the result
    }
	
	//converts days to unified date value
	public Date forecastDate(int days) {
		return null;
			
	}
	
	//Getter for the temperature forecast
	public double[] getTempForecast(String city, Date date) {
		return null;
		
	}
	
	//Method to suggest outfit for the temperature forecast
	public String[] outfitTempForecast(String city, Date period) {
		return null;
	}
		
	//Method to suggest outfit for the cloud forecast
	public String[] outfitCloudForecast(String city, Date period) {
		return null;
	}
			
	//Method to suggest outfit for the wind forecast
	public String[] outfitWindForecast(String city, Date period) {
		return null;
	}	
		
	//Method to suggest outfit for the rain forecast
	public String[] outfitRainForecast(String city, Date period) {
		return null;
	}	
	
	//Method to suggest outfit for the UV forecast
	public String[] outfitUVForecast(String city, Date period) {
		return null;
	}	
	
	
}
