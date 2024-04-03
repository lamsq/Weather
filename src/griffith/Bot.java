package griffith;

import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

public class Bot {
	//Attribute
	private OWM cwd;
	private double temp;
	private double cloud;
	private double wind;
	private double uv;
	
	
	
	//constructor
	public Bot(double temp) {
		this.setTemp(temp);
	}

	//Getter and Setter
	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
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
