package griffith;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
	public Bot(OWM owm) throws APIException {
		this.owm = owm; //openweathermap object that contains API key
		owm.setUnit(OWM.Unit.METRIC);//sets units to metric
		owm.setAccuracy(OWM.Accuracy.ACCURATE); //sets accuracy
		
		//wfd = owm.hourlyWeatherForecastByCityName(city);  //hourly weather forecast object by city name	
		
	}
	
	//get all suggestions for the current weather
	public String outfitCurrentWeather(String city) throws APIException {
		
		String outfit="The best choice would be:\n";
		
		outfit=outfit.concat(outfitTemp(city));
		outfit=outfit.concat(outfitCloud(city));
		outfit=outfit.concat(outfitWind(city));
		outfit=outfit.concat(outfitRain(city));
		outfit=outfit.concat(outfitUV(city));
		outfit=outfit.concat(";");
		
		return outfit;
	}

	//Getter for the current temperature
	public double getTemp(String city) throws APIException{
		cwd = owm.currentWeatherByCityName(city); //current weather object by city name
		return cwd.getMainData().getTemp(); //returns the value
	}
	
	//Check if the input is a city name
	public boolean isCityName(String city)  {
		
		try {
			cwd = owm.currentWeatherByCityName(city); //current weather object by city name
			return true; //returns true if there's such a city
		}
		catch (APIException e) {
			System.out.println("Incorrect city name, try again;");
			return false; //returns false if there's no such a city
		}
		  
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
				result = "; optional/no headwear";
			else if (cloud<20 && temp>=0)  //if clouds less than 20% and temperature equals or above 0
				result = "; headwear, sunglasses";
			else if (cloud<20 && temp>15)  //if clouds less than 20% and temperature above 15
				result = "; light headwear, sunglasses";
		}
		return result; //returns the result
    }
 	
	//outfit suggestions method (according to the wind)
	public String outfitWind(String city) throws APIException {
		cwd = owm.currentWeatherByCityName(city); //current weather object by city name
		String result = ""; //Variable to store output message
	    if(cwd.hasWindData() && getTemp(city)>=10) { //if there's wind data
	    	double wind = cwd.getWindData().getSpeed(); //Get wind speed
	    	if(wind>=3.4 && wind<5.4) //conditions for the light wind (speed from 3.4 to 5.4 m/s)
		    	result = "; light windjacket";
		    else if(wind>=5.4 && wind<7.9 ) //conditions for the moderate wind (speed from 5.4 to 7.9 m/s)
		    	result = "; windjacket";
		    else if(wind>=7.9) //conditions for the strong wind (speed from 7.9 m/s)
		    	result = "; windjacket, fleece/sweatshirt";
	    }
        return result; //returns the result
    }
	
	//outfit suggestions method (according to the rain)
	public String outfitRain(String city) throws APIException {
		cwd = owm.currentWeatherByCityName(city); //current weather object by city name
		String result = ""; //Variable to store output message
		if (cwd.hasRainData()) {
			double rain = cwd.getRainData().getPrecipVol3h(); //Get wind speed
		    if(rain>0 && getTemp(city)>=10) {
		    	result = "; rainjacket/umbrella";
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
			
			if(uv>4) //condition if uv is higher than 3
				result = "; sunscreen"; //offers to use sunscreen
		}
        return result; //returns the result
    }
	
	//converts days to unified date value
	public LocalDate[] forecastDate(int days) {
		
		LocalDate[] forecastDays = new LocalDate[days]; //creates the array of the dates to get forecast for
		LocalDate today = LocalDate.now(); //creates todays date
       
		for(int i=1; i<=days; i++) { //loop that iterates days times and adds next days to the array
			LocalDate futureDate = today.plusDays(i); //creates next date
			forecastDays[i-1] = futureDate; //adds next date
		}
		
		return forecastDays; //returns the array of the next days
			
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
	
	//method to process user input data for different requests (current/forecast/length of forecast)
	public HashMap<String, String[]> inputProcessing (String input) {
				
		return null;
	}
	
	
}
