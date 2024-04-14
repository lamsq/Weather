package griffith;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.*;



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
	public double getTemp() throws APIException{
		return cwd.getMainData().getTemp(); //returns the value
	}
	
	//outfit suggestions method (according to the temperature)
	public String outfitTemp() throws APIException{
		
		double temp = this.getTemp(); //gets current temperature in the chosen city
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
	public String outfitCloud() throws APIException{
		
		double temp = this.getTemp(); //gets current temperature in the chosen city
		double cloud = cwd.getCloudData().component1(); //gets current cloud data in the chosen city
		String result= ""; //creates the variable for return statement
		
		if (cloud>80 && temp>=0) //if clouds more than 80% and temperature equals or above 0
			result = "optional/no headwear";
		else if (cloud<20 && temp>=0)  //if clouds less than 20% and temperature equals or above 0
			result = "headwear, sunglasses";
		else if (cloud<20 && temp>15)  //if clouds less than 20% and temperature above 15
			result = "light headwear, sunglasses";
        return result; //returns the result
    }
	
	//outfit suggestions method (according to the wind)
	public String outfitWind() throws APIException {
		
	    double wind = cwd.getWindData().getSpeed(); //Get wind speed
	    String result = ""; //Variable to store output message
	    
	    if(wind>=3.4 && wind<5.4) //conditions for the light wind (speed from 3.4 to 5.4 m/s)
	    	result = "light windjacket";
	    else if(wind>=5.4 && wind<7.9) //conditions for the moderate wind (speed from 5.4 to 7.9 m/s)
	    	result = "windjacket";
	    else if(wind>=7.9) //conditions for the strong wind (speed from 7.9 m/s)
	    	result = "windjacket, fleece/sweatshirt";
	    
        return result; //returns the result
    }
	
	//outfit suggestions method (according to the rain)
	public String outfitRain() {
        return null;
    }
	
	//outfit suggestions method (according to the UV index)
	public String outfitUV() throws APIException {
		
		
        return null;
    }
	
	
	
}
