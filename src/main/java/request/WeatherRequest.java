package request;

public class WeatherRequest {

    private String key;
    private String URL;
    private String city;
    private String unit;

    public WeatherRequest(String city, String unit) {

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
