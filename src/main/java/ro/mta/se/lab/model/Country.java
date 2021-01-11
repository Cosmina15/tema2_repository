package ro.mta.se.lab.model;

public class Country
{
    public String country;
    public String city;
    public Country(String country, String city)
    {
        this.city = city;
        this.country = country;
    }

    public String getCountry() { return country; }
    public String getCity() { return city; }

    @Override
    public String toString() {
        return   getCity();
    }
}
