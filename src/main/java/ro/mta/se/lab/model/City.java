package ro.mta.se.lab.model;


public class City
{
    String cityName;
    String lat;
    String lon;
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
