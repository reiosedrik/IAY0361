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

    public String getInfo() {

        day1Lowest = Double.toString(Collections.min(temperaturesForDay1));
        day1Highest = Double.toString(Collections.max(temperaturesForDay1));
        day2Lowest = Double.toString(Collections.min(temperaturesForDay2));
        day2Highest = Double.toString(Collections.max(temperaturesForDay2));
        day3Lowest = Double.toString(Collections.min(temperaturesForDay3));
        day3Highest = Double.toString(Collections.max(temperaturesForDay3));


        return String.format("%s:\n%s: madalaim temperatuur: %s\u00b0C, kõrgeim temperatuur: %s\u00b0C.\n" +
                "%s: madalaim temperatuur: %s\u00b0C, kõrgeim temperatuur: %s\u00b0C.\n" +
                "%s: madalaim temperatuur: %s\u00b0C, kõrgeim temperatuur: %s\u00b0C.",
                city,
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
