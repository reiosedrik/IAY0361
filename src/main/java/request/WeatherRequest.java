package request;

import exceptions.WrongCityNameException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherRequest {

    private String key;
//    private String URL;
    private String city;
    private String units;


    private BufferedReader reader;

    private java.net.URL requestURL;
    private HttpURLConnection connection;
    private String requestAddress;

    private String temporaryStr;
    private String response;

    public WeatherRequest(String city, String units) {
        this.city = city;
        this.units = units;
        key = "bf1fda8842fddc67d78c7aced108cc2d";
        createURL();
    }

    private void createURL() {
        requestAddress = "http://api.openweathermap.org/data/2.5/forecast?q="
                + city
                + "&units=" + units
                + "&APPID="
                + key;
    }

    public String getJSONFromURL() throws WrongCityNameException {
        setupConnection();
        readFromConnection();
        disconnect();
        return response;
    }

    private void disconnect() {
        if (connection != null) {
            connection.disconnect();
        }
    }

    private void setupConnection() {
        try {
            requestURL = new URL(requestAddress);
            connection = (HttpURLConnection) requestURL.openConnection();
            connection.connect();
        } catch (IOException e) {
            e.printStackTrace();
            response = null;
        }
    }

    private void readFromConnection() throws WrongCityNameException {
        try {
//            System.out.println(connection.getInputStream());
            InputStreamReader r = new InputStreamReader(connection.getInputStream());
            reader = new BufferedReader(r);
            while ((temporaryStr = reader.readLine()) != null) {
                response = temporaryStr;
            }
        } catch (FileNotFoundException e) {
            throw new WrongCityNameException("Please enter correct name for cities.");
        } catch (IOException e) {
            e.printStackTrace();
            response = null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public String getRequestAddress() {
        return requestAddress;
    }

    public String getCity() {
        return city;
    }

    public String getUnits() {
        return units;
    }
}
