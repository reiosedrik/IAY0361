package report;

import coordinate.Coordinate;

public class CurrentWeatherReport {

    private double currentTemperature;
    private String city;
    private Coordinate coordinate;

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public String getInfo() {
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

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
