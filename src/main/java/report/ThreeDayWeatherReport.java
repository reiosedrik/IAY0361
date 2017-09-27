package report;

import coordinate.Coordinate;
import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.Map;

public class ThreeDayWeatherReport {

    public Map<String, Integer> lowestTemperatures;
    public Map<String, Integer> highestTemperatures;

    private int currentTemperature;
    private String city;
    private Coordinate coordinate;


    public int getLowestTemperatureForDay(String day) {
        return 0;
    }

    public int getHighestTemperatureForDay(String day) {
        return 0;
    }

    public ArrayList<String> getDays() {
        return null;
    }

    public Map<String, Integer> getLowestTemperatures() {
        return null;
    }

    public Map<String, Integer> getHighestTemperatures() {
        return null;
    }

    public int getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(int currentTemperature) {
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
