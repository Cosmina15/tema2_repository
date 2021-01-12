package ro.mta.se.lab.model;

/**
 * This class is used to models the entity City.
 */
public class City
{
    private String cityName;
    private String lat;
    private String lon;
    public City(String lat, String lon, String cityName)
    {
        this.lat = lat;
        this.lon = lon;
        this.cityName = cityName;
    }
    public City(){

    }
    public String getLon() { return lon; }
    public String getLat() { return lat; }
    public String getCityName() { return cityName; }
}
