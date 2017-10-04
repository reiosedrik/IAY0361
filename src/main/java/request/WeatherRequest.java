package request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherRequest {

    private String key;
    private String URL;
    private String city;
    private String unit;


    private BufferedReader reader;

    private java.net.URL requestURL;
    private HttpURLConnection connection;
    private String requestAddress;

    private String temporaryStr;
    private String response;

    public WeatherRequest(String city, String unit) {
        this.city = city;
        this.unit = unit;
    }

    public String getJSONFromURL() {
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

    private void readFromConnection() {
        try {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((temporaryStr = reader.readLine()) != null) {
                response = temporaryStr;
            }
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


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getCity() {
        return city;
    }

    public String getUnit() {
        return unit;
    }
}
