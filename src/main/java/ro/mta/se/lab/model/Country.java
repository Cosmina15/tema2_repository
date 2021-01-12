package ro.mta.se.lab.model;

import java.io.IOException;

/**
 * This class is used to models the entity Country .
 */
public class Country
{
    private String country;
    private String city;
    public Country(String country, String city)
    {
        this.city = city;
        this.country = country;
    }

    public String getCountry() { return country; }
    public String getCity() { return city; }

    @Override
    public String toString() {
        return  getCity();
    }
}
