package ro.mta.se.lab.model;
import static org.junit.Assert.*;
import org.junit.Test;

public class CountryTest{
    @Test
    public void testGetCountry() {
        Country country = new Country("RO", "Caracal");
        assertNotNull(country.getCountry());
    }
    @Test
    public void testGetCity() {
        Country country = new Country("RO", "Caracal");
        assertNotNull(country.getCity());
    }
    @Test
    public void testTestToString() {
        Country country = new Country("RO", "Caracal");
        assertNotNull(country.toString());
    }
}