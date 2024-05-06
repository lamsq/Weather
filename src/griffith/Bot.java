package griffith;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
		
	}
	
	//get all suggestions for the current weather
	public String outfitCurrentWeather(String city) throws APIException {
		
		//calls all the methods related to the ourfit for current weather
		String outfit="The best choice for "+city+" would be:\n";
		
		outfit=outfit.concat(outfitTemp(city));
		outfit=outfit.concat(outfitCloud(city));
		outfit=outfit.concat(outfitWind(city));
		outfit=outfit.concat(outfitRain(city));
		outfit=outfit.concat(outfitUV(city));
		outfit=outfit.concat(";");
		
		return outfit; //returns the result
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
		if (cwd.getWindData().getSpeed()!=null) {
			if(cwd.hasWindData() && getTemp(city)>=10) { //if there's wind data
		    	double wind = cwd.getWindData().getSpeed(); //Get wind speed
		    	if(wind>=3.4 && wind<5.4) //conditions for the light wind (speed from 3.4 to 5.4 m/s)
			    	result = "; light windjacket";
			    else if(wind>=5.4 && wind<7.9 ) //conditions for the moderate wind (speed from 5.4 to 7.9 m/s)
			    	result = "; windjacket";
			    else if(wind>=7.9) //conditions for the strong wind (speed from 7.9 m/s)
			    	result = "; windjacket, fleece/sweatshirt";
		    }			
		}	    
        return result; //returns the result
    }
	
	//outfit suggestions method (according to the rain)
	public String outfitRain(String city) throws APIException {
		cwd = owm.currentWeatherByCityName(city); //current weather object by city name
		String result = ""; //Variable to store output message		
		if (cwd.hasRainData()) {
			if (cwd.getRainData().getPrecipVol3h()!=null) {
				double rain = cwd.getRainData().getPrecipVol3h(); //Get wind speed
			    if(rain>0 && getTemp(city)>=10) {
			    	result = "; rainjacket/umbrella";
			    }
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
			double uv = 0;
			if(owm.currentUVIndexByCoords(lat, lon).getValue()!=null) {
				uv = owm.currentUVIndexByCoords(lat, lon).getValue(); //assigns the uv index value
			}
			if(uv>5) //condition if uv is higher than 3
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
	
	//get all suggestions for the current weather
	public String outfitForecastWeather(String city, LocalDate[] forecastDate) throws APIException {
		
		//calls all the methods related to the ourfit for current weather
		String outfit="The best choice for "+city+":\n";
		
		if (forecastDate.length==2) {
			
			LocalDate start=forecastDate[0];
		    LocalDate end=forecastDate[1];		    
		    LocalDate[] dates = new LocalDate[(int) (start.until(end, ChronoUnit.DAYS)+1)];
		    
		    for(int i=0; i<dates.length; i++) {		    	
		    	dates[i]=start.plusDays(i);		    	
		    }
		    
		    for(int i=0; i<dates.length; i++) {	
		    	outfit=outfit.concat(dates[i]+": \n");
		    	outfit=outfit.concat(outfitTempForecast(city, dates[i]));
				outfit=outfit.concat(outfitCloudForecast(city, dates[i]));
				outfit=outfit.concat(outfitWindForecast(city, dates[i]));
				outfit=outfit.concat(outfitRainForecast(city, dates[i]));
				outfit=outfit.concat(outfitUVForecast(city, dates[i]));
				outfit=outfit.concat(";\n\n");		    	
		    }
		    
		}
		else if (forecastDate.length==1){
			outfit=outfit.concat(forecastDate[0]+": \n");
			outfit=outfit.concat(outfitTempForecast(city, forecastDate[0]));
			outfit=outfit.concat(outfitCloudForecast(city, forecastDate[0]));
			outfit=outfit.concat(outfitWindForecast(city, forecastDate[0]));
			outfit=outfit.concat(outfitRainForecast(city, forecastDate[0]));
			outfit=outfit.concat(outfitUVForecast(city, forecastDate[0]));
			outfit=outfit.concat(";\n");
		}
		else {			
			System.out.println("\nSomething went wrong :(\nTry again...\n");			
		}		
		return outfit; //returns the result
	}	
	
	
	//Getter for the temperature forecast
	public ArrayList<HashMap<String, String>> getTempForecast(String city, LocalDate[] forecastDate) throws APIException {
		
		wfd = owm.hourlyWeatherForecastByCityName(city);  //hourly weather forecast object by city name		
		ArrayList<HashMap<String, String>> avgTempData = new ArrayList<HashMap<String, String>>(); //array data for hashmaps with temperatures and time
		
		for (int i=0; i<forecastDate.length; i++) { //loop that goes through the dates to forecast the weather
			
			HashMap<String, String> temps = new HashMap<String, String>(); //hashmap for output data			
			double tempData = 0; //the sum of avg temps through the day
			double tempMax = wfd.getDataList().get(0).getMainData().getTempMax(); //max temp during the day
			double tempMin = wfd.getDataList().get(0).getMainData().getTempMin(); //min temp during the day
			String timeMax = ""; //time of the max temp
			String timeMin = ""; //time of the min temp
			
			for (int j=0; j<wfd.getDataList().size(); j++) { //loop that goes through the temp data forecast
				
				if (wfd.getDataList().get(j).getDateTimeText().contains(forecastDate[i].toString())) { //if forecast date equals the requested date
					
					tempData=tempData+wfd.getDataList().get(j).getMainData().getTemp(); //sums the avg temp to the variable					
					if (tempMax<wfd.getDataList().get(j).getMainData().getTempMax()) { //checks if max temp is lower than max temp of the day
						//if true, sets the max temp and time
						tempMax = wfd.getDataList().get(j).getMainData().getTempMax(); 
						timeMax = wfd.getDataList().get(j).getDateTime().toString();
					}					
					if (tempMin>wfd.getDataList().get(j).getMainData().getTempMin()) { //checks if min temp is higher than max temp of the day
						//if true, sets the min temp and time
						tempMin = wfd.getDataList().get(j).getMainData().getTempMin();
						timeMin = wfd.getDataList().get(j).getDateTime().toString();
					}
					
				}else if(tempData!=0){ //if the sum of temperatures isnt 0, puts the data to the hashmap					
					temps.put("date", forecastDate[i].toString());
					temps.put("avg temp", Double.toString(tempData/8)); //puts the avg temp, divided by 8, since we have forecast every 3 hre (24/3 = 8)
					temps.put("max temp", Double.toString(tempMax)); //puts max temp
					temps.put("max time", timeMax ); //puts max time
					temps.put("min temp", Double.toString(tempMin)); //puts min temp
					temps.put("min time", timeMin ); //puts min time	
				}
			}
			avgTempData.add(temps); //adds hashmaps to trhe arraylist
		}
		return avgTempData; //returns the hashmap with data
	}
	
	//Method to suggest outfit for the temperature forecast
	public String outfitTempForecast(String city, LocalDate date) throws APIException {
		
	    wfd = owm.hourlyWeatherForecastByCityName(city);  //hourly weather forecast object by city name	    
	    ArrayList<HashMap<String, String>> temps = this.getTempForecast(city, new LocalDate[]{date}); //gets temp data for 
	    
	    //sets average, max and min temperatures
	    double temp = Double.valueOf(temps.get(0).get("avg temp"));
	    double maxTemp = Double.valueOf(temps.get(0).get("max temp"));
	    double minTemp = Double.valueOf(temps.get(0).get("min temp"));
	    String result = ""; //creates the variable for return statement		    
	    
	    if(maxTemp-minTemp>5) {	//condition that checks if the difference between max and min temp is more than 5 degrees   		    
	    	temp = minTemp; //sets temperature as min temperature
	    }	    
	    
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
        	//returns the result
		return result;	    		
	}
	
	//Method to suggest outfit for the cloud forecast
	public String outfitCloudForecast(String city, LocalDate date) throws APIException {
		
		wfd = owm.hourlyWeatherForecastByCityName(city);  //hourly weather forecast object by city name			
		ArrayList<HashMap<String, String>> temps = this.getTempForecast(city, new LocalDate[]{date}); //gets temp data for 		    
		//sets average, max and min temperatures
		double temp = Double.valueOf(temps.get(0).get("avg temp"));
		double maxTemp = Double.valueOf(temps.get(0).get("max temp"));
		double minTemp = Double.valueOf(temps.get(0).get("min temp"));   
		
		if(maxTemp-minTemp>5) {	//condition that checks if the difference between max and min temp is more than 5 degrees   		    
	    	temp = minTemp; //sets temperature as min temperature
	    }
		
		double cloud = 100;
		for(int l=0; l<wfd.getDataList().size(); l++) {			
			if (wfd.getDataList().get(l).getDateTimeText().contains(date.toString())) {
				if(cloud>wfd.getDataList().get(l).getCloudData().getCloud()) {
					cloud=wfd.getDataList().get(l).getCloudData().getCloud();
				}
			}
		}
		
		String result= ""; //creates the variable for return statement		
		if(cwd.hasCloudData()) { //if there's cloud data			
			if (cloud>80 && temp>=0) //if clouds more than 80% and temperature equals or above 0
				result = "; optional/no headwear";
			else if (cloud<20 && temp>=0)  //if clouds less than 20% and temperature equals or above 0
				result = "; headwear, sunglasses";
			else if (cloud<20 && temp>15)  //if clouds less than 20% and temperature above 15
				result = "; light headwear, sunglasses";
		}
		return result; //returns the result		
	}
	
	//Method to suggest outfit for the wind forecast
	public String outfitWindForecast(String city, LocalDate date) throws APIException {
		
		wfd = owm.hourlyWeatherForecastByCityName(city);  //hourly weather forecast object by city name	
		ArrayList<HashMap<String, String>> temps = this.getTempForecast(city, new LocalDate[]{date}); //gets temp data for 		    
		//sets average, max and min temperatures
		double temp = Double.valueOf(temps.get(0).get("avg temp"));
		double maxTemp = Double.valueOf(temps.get(0).get("max temp"));
		double minTemp = Double.valueOf(temps.get(0).get("min temp"));   
		
		if(maxTemp-minTemp>5) {	//condition that checks if the difference between max and min temp is more than 5 degrees   		    
	    	temp = minTemp; //sets temperature as min temperature
	    }
		
		double wind = 0;
		for(int l=0; l<wfd.getDataList().size(); l++) {			
			if (wfd.getDataList().get(l).getDateTimeText().contains(date.toString())) {
				if(wfd.getDataList().get(l).getWindData().getSpeed()!=null && wind<wfd.getDataList().get(l).getWindData().getSpeed()) {
					wind=wfd.getDataList().get(l).getCloudData().getCloud();
				}
			}
		}
		
		String result = ""; //Variable to store output message		
		if(temp>=10) { //if there's wind data	    	
	    	if(wind>=3.4 && wind<5.4) //conditions for the light wind (speed from 3.4 to 5.4 m/s)
		    	result = "; light windjacket";
		    else if(wind>=5.4 && wind<7.9 ) //conditions for the moderate wind (speed from 5.4 to 7.9 m/s)
		    	result = "; windjacket";
		    else if(wind>=7.9) //conditions for the strong wind (speed from 7.9 m/s)
		    	result = "; windjacket, fleece/sweatshirt";
	    }			    
        return result; //returns the result
	}	
		
	//Method to suggest outfit for the rain forecast
	public String outfitRainForecast(String city, LocalDate date) throws APIException {
		
		wfd = owm.hourlyWeatherForecastByCityName(city);  //hourly weather forecast object by city name	
		ArrayList<HashMap<String, String>> temps = this.getTempForecast(city, new LocalDate[]{date}); //gets temp data for 		    
		//sets average, max and min temperatures
		double temp = Double.valueOf(temps.get(0).get("avg temp"));
		double maxTemp = Double.valueOf(temps.get(0).get("max temp"));
		double minTemp = Double.valueOf(temps.get(0).get("min temp")); 		
		if(maxTemp-minTemp>5) {	//condition that checks if the difference between max and min temp is more than 5 degrees   		    
	    	temp = minTemp; //sets temperature as min temperature
	    }		
		boolean rain = false;
		for(int l=0; l<wfd.getDataList().size(); l++) {			
			if (wfd.getDataList().get(l).getDateTimeText().contains(date.toString())) {
				if(wfd.getDataList().get(9).getWeatherList().get(0).getMainInfo().contains("rain")) {
					rain=true;
					break;
				}
			}
		}		
		
		String result = ""; //Variable to store output message			
		if (rain && temp>=10) {			   
		    result = "; rainjacket/umbrella";		    
		}		
        return result; //returns result
	}	
	
	//Method to suggest outfit for the UV forecast
	public String outfitUVForecast(String city, LocalDate date) throws APIException {		
		
		wfd = owm.hourlyWeatherForecastByCityName(city);  //hourly weather forecast object by city name			
		ArrayList<HashMap<String, String>> temps = this.getTempForecast(city, new LocalDate[]{date}); //gets temp data for 		    
		String result = "";
		double index = 0;
		double lat, lon; //initialize variables for city coordinates
		
		if (wfd.getCityData().hasCoordData()) {			
			lat = wfd.getCityData().getCoordData().getLatitude(); //assigns the latitude
			lon = wfd.getCityData().getCoordData().getLongitude(); //assigns the longitude
			
			for (int k=0; k<owm.dailyUVIndexForecastByCoords(lat, lon).size(); k++ ) {				
				if(((owm.dailyUVIndexForecastByCoords(lat, lon).get(k).getDateTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).toString().contains(date.toString())) {					
					if(index<owm.dailyUVIndexForecastByCoords(lat, lon).get(k).getValue()) {						
						index = owm.dailyUVIndexForecastByCoords(lat, lon).get(k).getValue();
					}					
				}				
			}			
		}	
		
		if(index>5) { //condition if uv is higher than 3 
			result = "; sunscreen"; //offers to use sunscreen
		}
        return result; //returns the result
	}	
	
	//method to process user input data for different requests (current/forecast/length of forecast)
	public HashMap<String, ArrayList<String>> inputProcessing (String input) {
		
		//datastructure that will be returned and contains data about the cities and dates for the forecast
		HashMap<String, ArrayList<String>> inputData = new HashMap<String, ArrayList<String>>();		
		String[] inputArray; //array for the user input		
		if (!input.contains(" ")) { //if input contains only 1 word
			inputArray = new String[1]; //assigns the new array with 1 element
			inputArray[0] = input; //sets the element
		} //if there are more than 1 element
		else {
			inputArray = input.split(" "); //splits user data to array
		}
		
		
		ArrayList<String> cities = new ArrayList<>(); //creates the arraylist with cities		
		for (int i=0; i<inputArray.length; i++) { //loop that goes through the user data
			if(isCityName(inputArray[i]) && !inputArray[i].equals("days") && !inputArray[i].equals("day"))  //condition that checks if city with this name exists
				cities.add(inputArray[i]); //adds city to the arraylist
			else if (isCityName(inputArray[i]) && inputArray[i].equals("Days") && inputArray[i].equals("Day"))
				cities.add(inputArray[i]); //adds city to the arraylist
		}
		inputData.put("city", cities); //puts the data to the hashmap
		
		ArrayList<String> dates = new ArrayList<>(); //creates the arraylist with dates		
		for (int i=0; i<inputArray.length; i++) { //loop that goes through the user data
			if(inputArray[i].matches("\\b(?:0?[1-9]|[12]\\d|3[01])[-,\\/.](?:0?[1-9]|1[0-2])[-,\\/.](?:\\d{4}|\\d{2})\\b")) { //condition that checks if city with this name exists
				dates.add(inputArray[i]); //adds start date to the arraylist 				
			}
		}
		
		
		ArrayList<String> localDates = new ArrayList<>(); //creates the arraylist with dates in string format		
		ArrayList<String> startStrLocalDates = new ArrayList<>(); //creates the arraylist with start dates
		ArrayList<String> endStrLocalDates = new ArrayList<>(); //creates the arraylist with end dates			
		for (int i=0; i<inputArray.length; i++) { //loop that goes through the user data
			if(inputArray[i].length()>1 && (inputArray[i].contains(".") || inputArray[i].contains("-") || inputArray[i].contains("/") || inputArray[i].contains("\\") || inputArray[i].contains(","))) { //condition that checks if the string contain special characters
				String[] dateParts = inputArray[i].split("[-,\\/.]");		 //splits date to day mon aand year		
				String year =""; //year string format
				String mon =""; //month string format
				String day =""; //day string format
				int yearInt =0; //year integer format
				int monInt =0; //month integer format
				int dayInt =0; //day integer format
				int currentYear = LocalDate.now().getYear(); //current day
				int currentMon = LocalDate.now().getMonthValue(); //current month
				int currentDate = LocalDate.now().getDayOfMonth(); //current year					
				for(int r=0; r<dateParts.length; r++) { //loop that goes through the year month and date
					try { //try catch block to prevent exceptions in case of incorrect format
						int temp = Integer.valueOf(dateParts[r]); //parses date to integer
						if(dateParts[r].length()==4 && temp==currentYear) { //conditions for year
							//sets year string and integer
							year = dateParts[r];
							yearInt = Integer.valueOf(dateParts[r]);
						} //condition for month
						else if((temp==currentMon && dayInt!=0) || (temp==currentMon+1 && currentDate<25 && dayInt!=0) || (temp==2 && currentDate<23 && dayInt!=0)){ //condition for month 
							if(dateParts[r].length()==1) {//if month doesnt have 0 before the date
								//sets month string and integer variables
								mon = "0"+dateParts[r];
								monInt = Integer.valueOf(mon);
							}
							else { //if theres 0 before the date
								//sets month string and integer variables
								mon = dateParts[r];
								monInt = Integer.valueOf(dateParts[r]);
							}
						}
						else if (temp<=31){ //condition for date
							if (dateParts[r].length()==1) { //if date doesnt have 0 before the date
								//sets string and integer variables
								day = "0"+dateParts[r];
								dayInt = Integer.valueOf(day);
							}
							else { //if theres 0 before the date
								//sets string and integer variables
								day = dateParts[r];
								dayInt = Integer.valueOf(dateParts[r]);
							}
						}
						else { //if date doesnt fit the requirements
							System.out.println("Incorrect date format;");
							return null;
						}
					} //in case of incorrect format (catch block)
					catch (Exception e) {
						System.out.println("Incorrect date format;");
						return null;
					}
				}
				//if date suits the requirements for year or year and date
				if(yearInt == currentYear || (yearInt == currentYear+1 && monInt==12 && dayInt>26)) {							
					localDates.add((year+"-"+mon+"-"+day));	 //adds the date to the arraylist						
				} 							
			}
		}
		
		if(localDates.size()==inputData.get("city").size()) { //condition for one day forecast for each city   inputData.get("city").size()
			for (int q=0; q<localDates.size(); q++) { //loop goes through the dates  
				startStrLocalDates.add(localDates.get(q));	//puts dates to the corresponding arraylist
			}
		}
		else { //condition for a few days forecast for each city 
			for (int q=0; q<localDates.size(); q++) { //loop that goes through dates
				if(localDates.indexOf(localDates.get(q))%2==0) //forecast start dates
					startStrLocalDates.add(localDates.get(q)); //adds date to the array list
				else  //forecast end dates
					endStrLocalDates.add(localDates.get(q)); //adds date to the array list
			}
		}	
		//puts data to the hashmap
		inputData.put("start local dates", startStrLocalDates); 
		inputData.put("end local dates", endStrLocalDates);	
		inputData.put("local dates", localDates);
		
		
		
		ArrayList<String> startDates = new ArrayList<>(); //creates the arraylist with start dates
		ArrayList<String> endDates = new ArrayList<>(); //creates the arraylist with end dates
		boolean forecast=false;		
		
		if (input.contains("next") || input.contains("forecast") || input.contains("days")) { //checks if user wants to get results for the upcoming days
			for (int i=0; i<inputArray.length; i++) { //loops through the data
				if(inputArray[i].matches("(^|\s+)([1-5])($|\s+)")) { //regex to find the number of days
					int days = Integer.valueOf(inputArray[i]) ; //casts string to int					
					for (int z=0; z<this.forecastDate(days).length; z++) {
						String d = this.forecastDate(days)[z].toString();						
						dates.add(d);
					}
					forecast = true;					
				}
			}
		}		
		if (forecast) {
			startDates.add(dates.get(0));	//puts dates to the corresponding arraylist
			endDates.add(dates.get(dates.size()-1)); //adds date to the array list
		}
		else if(dates.size()==inputData.get("city").size()) { //condition for one day forecast for each city
			for (int i=0; i<dates.size(); i++) { //loop goes through the dates 
				startDates.add(dates.get(i));	//puts dates to the corresponding arraylist
			}
		}
		else { //condition for a few days forecast for each city 
			for (int i=0; i<dates.size(); i++) { //loop that goes through dates
				if(dates.indexOf(dates.get(i))%2==0) //forecast start dates
					startDates.add(dates.get(i)); //adds date to the array list
				else  //forecast end dates
					endDates.add(dates.get(i)); //adds date to the array list
			}
		}		
		//puts data to the hashmap
		inputData.put("start", startDates); 
		inputData.put("end", endDates); 
		
		
		ArrayList<String> mode = new ArrayList<>(); //mode for the weather outfits
		//if city was not recognised
		if (inputData.get("city").size()==0){
			System.out.println("We checked the map, there is no city with this name;"); //prints error message
			return null; //returns null
		}
		//condition for the single day forecast
		else if (inputData.get("city").size()==inputData.get("start").size() && inputData.get("end").size()==0 || (forecast && dates.size()==1)) {
			mode.add("single forecast");
			inputData.put("mode", mode); //puts mode
		}
		//condition for the multiple days forecast
		else if (inputData.get("city").size()==inputData.get("start").size() && inputData.get("end").size()==inputData.get("start").size() && inputData.get("city").size()==inputData.get("end").size() || (forecast && dates.size()>1)) {
			mode.add("period forecast");
			inputData.put("mode", mode);
		}		
		//condition for the current weather
		else if (inputData.get("city").size()!=0 && inputData.get("start").size()==0 && inputData.get("end").size()==0) {
			mode.add("current weather");
			inputData.put("mode", mode); //puts mode
		}
		else {
			System.out.println("Hmmm...I'm confused, can you repeat the request with specified dates?");
		}
		
		return inputData; //returns processed data
	}	

	
}
