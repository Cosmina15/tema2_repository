package ro.mta.se.lab.controller;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import ro.mta.se.lab.model.Country;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class is used to test the get functionality of Controller class
 * @author Cosmina Barbu
 */
public class ControllerTest {
    private  Controller test;
    private  Country country;
    /**
     * This method is used to initialize the Country object
     */
    @Before
    public void init()
    {
       country = new Country("RO", "Caracal");
       country = mock(Country.class);
    }
    /**
     * This method is used to test getTemperature method
     */
    @Test
    void getTemperatureTest() throws IOException, ParseException {
        test = new Controller();
        country = mock(Country.class);
        when(country.getCountry()).thenReturn("RO");
        when(country.getCity()).thenReturn("Caracal");
        assertDoesNotThrow(() -> test.getTemperature(country.getCity()));
        System.out.println("Controller get method is ok " + test.getTemperature(country.getCity()));
    }
}
