package ro.mta.se.lab.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ro.mta.se.lab.model.City;
import ro.mta.se.lab.model.Country;

import ro.mta.se.lab.ReadFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.util.*;

public class Controller implements Initializable
{
    @FXML private Label labelCountry;
    @FXML private Label labelCity;
    @FXML private ComboBox<String> comboBoxCountry;
    @FXML private ComboBox<String> comboBoxCity;
    @FXML private Label labelTemp;
    @FXML private Label labelSpeedWind;
    @FXML private Label labelHumidity;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        labelCountry.setText("Țară");
        labelCity.setText("Oraș");
        ro.mta.se.lab.ReadFile readFile = new ReadFile();
        readFile.main();
        for (Map.Entry<String, List<Country>> entry: readFile.citiesByCountry.entrySet()) {
            comboBoxCountry.getItems().add(entry.getKey());
        }
        comboBoxCountry.setPromptText("Alege o țară");
        comboBoxCity.setPromptText("Alege un oraș");

    }

    public void whenComboBoxCountryIsSelected() throws IOException
    {
        ReadFile readFile = new ReadFile();
        readFile.main();
        comboBoxCity.getItems().clear();
        labelSpeedWind.setText("");
        labelTemp.setText("");
        labelHumidity.setText("");
        for (Map.Entry<String, List<Country>> entry: readFile.citiesByCountry.entrySet())
        {
            if(comboBoxCountry.getValue().toString().equals(entry.getKey().toString()))
            {
                String citiesValues =  entry.getValue().toString().split("[\\[\\]]")[1];
                String[] citiesTokens = citiesValues.split(",\\s");
                comboBoxCity.getItems().add(citiesTokens[0]);
                comboBoxCity.getItems().add(citiesTokens[1]);
            }
        }
    }

    public String whenCityIsSelected() throws IOException, ParseException
    {
        labelSpeedWind.setText("");
        labelTemp.setText("");
        labelHumidity.setText("");
        ReadFile readFile = new ReadFile();
        readFile.main();
        String cityName = null;
        for(City city : readFile.cityDetails)
        {
            if( comboBoxCity.getValue() != null ) {
                if (comboBoxCity.getValue().toString().equals(city.getCityName())) {
                    cityName = comboBoxCity.getValue().toString();
                }
            }
        }
      return cityName;
    }
    public void getWheater(String citySelected) throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        try
        {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+citySelected+"&APPID=b74538cdb116a1530d534ca34249ca61\n");
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );

            JSONObject jsonObject = (JSONObject)jsonParser.parse(reader);
            JSONObject main = (JSONObject)jsonObject.get( "main" );

            Long dt = (Long)jsonObject.get("dt");
            Instant instant = Instant.ofEpochSecond( dt );
            System.out.println(instant);
            Double temp = (Double)main.get("temp");

            if(temp != null)
            {
                labelTemp.setText("Temperatura este: " + temp.toString() + "K");
            }
            else
            {
                labelTemp.setText("");
            }

            JSONObject wind = (JSONObject)jsonObject.get("wind");
             Object speed = wind.get( "speed" );
            if(speed != null)
            {
                labelSpeedWind.setText("Vantul bate cu: " + speed.toString() + "m/s");
            }
            else
            {
                labelSpeedWind.setText("");
            }
            Long humidity = (Long) main.get("humidity");
            if(humidity != null)
            {
                labelHumidity.setText("Umiditate de: " + humidity.toString() + "%");
            }
            else
            {
                labelHumidity.setText("");
            }
            System.out.println( humidity );
            System.out.println( "\n" );

        }
        catch ( FileNotFoundException e )
        {
            e.printStackTrace();
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        catch ( ParseException e )
        {
            e.printStackTrace();
        }
    }
    public void getWheaterDetails(String citySelected) throws IOException, ParseException
    {
        getWheater(citySelected);
    }
    public void showWheaterDetails() throws IOException, ParseException 
    {
     getWheaterDetails(whenCityIsSelected());
    }
}
