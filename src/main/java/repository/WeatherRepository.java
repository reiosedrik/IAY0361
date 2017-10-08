package repository;

import report.CurrentWeatherReport;
import report.ThreeDayWeatherReport;
import request.WeatherRequest;

import java.text.SimpleDateFormat;
import java.util.*;

public class WeatherRepository {

    private List<String> lines;
    private List<Double> temperaturesForDay1 = new ArrayList<>();
    private List<Double> temperaturesForDay2 = new ArrayList<>();
    private List<Double> temperaturesForDay3 = new ArrayList<>();
    private String day1FromNow;
    private String day2FromNow;
    private String day3FromNow;


    public CurrentWeatherReport getCurrentWeatherReport(WeatherRequest request) {
        getWeatherDataAsLines(request);
        String temperature = loopThroughLinesForFirstTemperature();
        CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport();
        currentWeatherReport.setCurrentTemperature(Double.parseDouble(temperature));
        currentWeatherReport.setCity(request.getCity());
        return currentWeatherReport;
    }

    public ThreeDayWeatherReport getThreeDayWeatherReport(WeatherRequest request) {
        getWeatherDataAsLines(request);
        getNextThreeDaysFromNow();
        loopThroughLinesForThreeDays();
        ThreeDayWeatherReport report = new ThreeDayWeatherReport();
        report.setTemperatures(temperaturesForDay1, temperaturesForDay2, temperaturesForDay3);
        report.setDays(day1FromNow, day2FromNow, day3FromNow);
        report.setCity(request.getCity());
        return report;
    }

    public void getWeatherDataAsLines(WeatherRequest request) {
        String outputFromRequest = request.getJSONFromURL();
        lines = getLinesFromOutput(outputFromRequest);
    }


    public String loopThroughLinesForFirstTemperature() {
        String temperature = "";
        for (String line : lines) {
            try {
                if (line.substring(9, 13).equals("temp")) {
                    temperature =  line.substring(15);
                }
            } catch (StringIndexOutOfBoundsException e) {
                // occurs only with lines we don't need
            }
        }
        return temperature;
    }


    public void loopThroughLinesForThreeDays() {
        String day = null;

        for (String line : lines) {
            try {
                String newDay = checkLineForDay(line);
                if (newDay != null) {
                    day = newDay;
                }

                if (day != null) {
//                    System.out.println(line + "     " + line.substring(9, 13));
                    if (line.substring(9, 13).equals("temp")) {
                       addTemperatureForDay(day, line);
                    }
                }
            } catch (StringIndexOutOfBoundsException e) {
                //
            }
        }
    }

    private void addTemperatureForDay(String day, String line) {
        if (day.equals(day1FromNow)) {
            temperaturesForDay1.add(getTemperatureFromLine(line));
        } else if (day.equals(day2FromNow)) {
            temperaturesForDay2.add(getTemperatureFromLine(line));
        } else if (day.equals(day3FromNow)) {
            temperaturesForDay3.add(getTemperatureFromLine(line));
        }
    }

    public String checkLineForDay(String line) {
        if (line.substring(2, 4).equals("dt")) {
            return getDayFromUNIX(line.substring(6));
        } else if (line.substring(10, 12).equals("dt")) {
            return getDayFromUNIX(line.substring(14));
        }
        return null;
    }

    private void getNextThreeDaysFromNow() {
        day1FromNow = getNthDayFromNow(1);
        day2FromNow = getNthDayFromNow(2);
        day3FromNow = getNthDayFromNow(3);
    }

    public double getTemperatureFromLine(String line) {
        return Double.parseDouble(line.substring(15));
    }

    public List<String> getLinesFromOutput(String output) {
        String[] lines = output.split(",");
        return Arrays.asList(lines);
    }

    public String getDayFromUNIX(String unixTimeStamp) {
        int unixTime = Integer.parseInt(unixTimeStamp);
        Date date = new Date();
        date.setTime((long) unixTime * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC +02:00"));
        return dateFormat.format(date);
    }

    public String getNthDayFromNow(int n) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC +02:00"));
        date = addNDaysToDate(date, n);
        return dateFormat.format(date);
    }

    public Date addNDaysToDate(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, amount);
        return calendar.getTime();
    }

    public List<String> getLines() {
        return lines;
    }

    public List<Double> getTemperaturesForDay1() {
        return temperaturesForDay1;
    }

    public List<Double> getTemperaturesForDay2() {
        return temperaturesForDay2;
    }

    public List<Double> getTemperaturesForDay3() {
        return temperaturesForDay3;
    }

    public String getDay1FromNow() {
        return day1FromNow;
    }

    public String getDay2FromNow() {
        return day2FromNow;
    }

    public String getDay3FromNow() {
        return day3FromNow;
    }
}
