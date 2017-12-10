package report;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ThreeDayWeatherReport {

    List<Double> temperaturesForDay1 = new ArrayList<>();
    List<Double> temperaturesForDay2 = new ArrayList<>();
    List<Double> temperaturesForDay3 = new ArrayList<>();
    public List<String> threeDays = new ArrayList<>();
    String day1Lowest;
    String day1Highest;
    String day2Lowest;
    String day2Highest;
    String day3Lowest;
    String day3Highest;
    private String city;
    private String temperatureSymbol;

    public ThreeDayWeatherReport(String units) {
        if (units.equals("imperial")) {
            temperatureSymbol = "\u00b0F";
        } else {
            temperatureSymbol = "\u00b0C";
        }
    }

    public String getInfo() {

        day1Lowest = Double.toString(Collections.min(temperaturesForDay1)) + temperatureSymbol;
        day1Highest = Double.toString(Collections.max(temperaturesForDay1)) + temperatureSymbol;
        day2Lowest = Double.toString(Collections.min(temperaturesForDay2)) + temperatureSymbol;
        day2Highest = Double.toString(Collections.max(temperaturesForDay2)) + temperatureSymbol;
        day3Lowest = Double.toString(Collections.min(temperaturesForDay3)) + temperatureSymbol;
        day3Highest = Double.toString(Collections.max(temperaturesForDay3)) + temperatureSymbol;


        return String.format("\n%s: min temp: %s, max temp: %s.\n" +
                "%s: min temp: %s, max temp: %s.\n" +
                "%s: min temp: %s, max temp: %s.",
                threeDays.get(0), day1Lowest, day1Highest,
                threeDays.get(1), day2Lowest, day2Highest,
                threeDays.get(2), day3Lowest, day3Highest);
    }


    public List<String> getDays() {
        return threeDays;
    }


    public void setTemperatures(List<Double> temperaturesForDay1, List<Double> temperaturesForDay2
    , List<Double> temperaturesForDay3) {
        this.temperaturesForDay1 = temperaturesForDay1;
        this.temperaturesForDay2 = temperaturesForDay2;
        this.temperaturesForDay3 = temperaturesForDay3;
    }

    public void setDays(String day1, String day2, String day3) {
        threeDays.add(day1);
        threeDays.add(day2);
        threeDays.add(day3);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
