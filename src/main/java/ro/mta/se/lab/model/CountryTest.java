package ro.mta.se.lab.model;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * This class is used to unit test the functionalities of Country class
 */
public class CountryTest{
    /**
     * This method is used to test getCountry method
     */
    @Test
    public void testGetCountry() {
        Country country = new Country("RO", "Caracal");
        assertNotNull(country.getCountry());
    }
    /**
     * This method is used to test getCity method
     */
    @Test
    public void testGetCity() {
        Country country = new Country("RO", "Caracal");
        assertNotNull(country.getCity());
    }
    /**
     * This method is used to test toString method
     */
    @Test
    public void testTestToString() {
        Country country = new Country("RO", "Caracal");
        assertNotNull(country.toString());
    }
}