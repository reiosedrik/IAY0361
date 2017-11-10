package report;


public class CurrentWeatherReport {

    private double currentTemperature;
    private String city;

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public String getInfo() {
        if (city == null) {
            return "";
        }
        return String.format("%s: Praegune temperatuur on %s\u00b0C",city, Double.toString(currentTemperature));
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
