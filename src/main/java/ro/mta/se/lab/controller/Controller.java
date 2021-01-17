package ro.mta.se.lab.controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ro.mta.se.lab.WriteToFile;
import ro.mta.se.lab.model.City;
import ro.mta.se.lab.model.Country;
import ro.mta.se.lab.ReadFile;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.util.*;

/**
 * This class is used to implement the functionalities of application
 * @author Cosmina Barbu
 */
public class Controller implements Initializable
{
    @FXML private Label labelCountry;
    @FXML private Label labelCity;
    @FXML private ComboBox<String> comboBoxCountry;
    @FXML private ComboBox<String> comboBoxCity;
    @FXML private Label labelTemp;
    @FXML private Label labelSpeedWind;
    @FXML private Label labelHumidity;
    @FXML private Label labelCityName;

    /**
     * This method is used to initialize labels for Country and City and set a prompt text for them, to initialize values for combobox Country.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        labelCountry.setText("Țară");
        labelCity.setText("Oraș");
        ro.mta.se.lab.ReadFile readFile = new ReadFile();
        readFile.readFile();
        for (Map.Entry<String, List<Country>> entry: readFile.citiesByCountry.entrySet()) {
            comboBoxCountry.getItems().add(entry.getKey());
        }
        comboBoxCountry.setPromptText("Alege o țară");
        comboBoxCity.setPromptText("Alege un oraș");

    }

    /**
     * This method is used to populate the combobox for City with values when a specific Country is selected.
     * @throws IOException
     */
    public void whenComboBoxCountryIsSelected() throws IOException
    {
        ReadFile readFile = new ReadFile();
        readFile.readFile();
        comboBoxCity.getItems().clear();
        labelSpeedWind.setText("");
        labelTemp.setText("");
        labelHumidity.setText("");
        labelCityName.setText("");
        for (Map.Entry<String, List<Country>> entry: readFile.citiesByCountry.entrySet())
        {
            if(comboBoxCountry.getValue().equals(entry.getKey()))
            {
                String citiesValues =  entry.getValue().toString().split("[\\[\\]]")[1];
                String[] citiesTokens = citiesValues.split(",\\s");
                comboBoxCity.getItems().add(citiesTokens[0]);
                comboBoxCity.getItems().add(citiesTokens[1]);
            }
        }
    }

    /**
     * This method is used to return the name of the City selected.
     * @throws IOException
     * @throws ParseException
     */
    public String whenCityIsSelected() throws IOException, ParseException
    {
        labelSpeedWind.setText("");
        labelTemp.setText("");
        labelHumidity.setText("");
        labelCityName.setText("");
        ReadFile readFile = new ReadFile();
        readFile.readFile();
        String cityName = null;
        for(City city : readFile.cityDetails)
        {
            if( comboBoxCity.getValue() != null ) {
                if (comboBoxCity.getValue().equals(city.getCityName())) {
                    cityName = comboBoxCity.getValue();
                }
            }
        }
      return cityName;
    }

     public Double getTemperature(String citySelected) throws IOException, ParseException {
         JSONParser jsonParser = new JSONParser();

             URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+citySelected+"&APPID=b74538cdb116a1530d534ca34249ca61\n");
             URLConnection conn = url.openConnection();
             BufferedReader reader = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );

             JSONObject jsonObject = (JSONObject)jsonParser.parse(reader);
             JSONObject main = (JSONObject)jsonObject.get( "main" );

             Long dt = (Long)jsonObject.get("dt");
             Instant instant = Instant.ofEpochSecond( dt );
             System.out.println(instant);
             Double temp = (Double)main.get("temp");
             return temp;
     }
    /**
     * This method is used get wheater details for that City selected,
     * it makes a connection to API WheterMap, get json object and parse it
     * to extract interested values.
     * @throws IOException
     * @throws ParseException
     */
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
            Double temp = getTemperature(citySelected);

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
            JSONArray wheaterArray = (JSONArray) jsonObject.get("weather");
            JSONObject wheater= (JSONObject) wheaterArray.get(0);
            String wheaterString= (String) wheater.get("main");

            if(wheaterString != null)
            {
                labelCityName.setText(citySelected + ": " + wheaterString);
            }
            else
            {
                labelCityName.setText("");
            }

            WriteToFile writer = new WriteToFile();
            writer.writeToFile(citySelected);
            writer.writeToFile("Temperatura este: " + temp.toString() + "K");
            writer.writeToFile("Vantul bate cu: " + speed.toString() + "m/s");
            writer.writeToFile("Umiditate de: " + humidity.toString() + "%");

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
    /**
     * These methods are used to show the details of Wheater when the button is selected.
     * @throws IOException
     * @throws ParseException
     */
    public void getWheaterDetails(String citySelected) throws IOException, ParseException
    {
        getWheater(citySelected);
    }
    public void showWheaterDetails() throws IOException, ParseException 
    {
     getWheaterDetails(whenCityIsSelected());
    }
}
