package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import griffith.*;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

class TestTemperature {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testTemperature() throws APIException { //tests the kelvinToCelcius method
		
		//creating an object
		OWM owm = new OWM("bd1e2a9675bcd866cce494364b798612");
		CurrentWeather cwd = owm.currentWeatherByCityName("Dublin");
	    //assigns the result of a method execution
	    double actual = Main.kelvinToCelcius(cwd.getMainData().getTemp()); 
	    //expected value
	    double expected = 44;
	    //comparing two values
	    assertEquals(actual, expected, 0.3);
		
	}

}
