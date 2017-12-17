package repository;

import exceptions.WrongCityNameException;
import report.CoordinateReport;
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
    private String units;
    private Date currentDate = new Date();

    public WeatherRepository(WeatherRequest request) throws WrongCityNameException {
        this.units = request.getUnits();
        getWeatherDataAsLines(request);
    }

    public WeatherRepository() {
    }

    public CurrentWeatherReport getCurrentWeatherReport() {
        String temperature = loopThroughLinesForFirstTemperature();
        CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport(units);
        currentWeatherReport.setCurrentTemperature(Double.parseDouble(temperature));
        return currentWeatherReport;
    }

    public ThreeDayWeatherReport getThreeDayWeatherReport() {
        getNextThreeDaysFrom(currentDate);
        loopThroughLinesForThreeDayTemperatures();
        ThreeDayWeatherReport report = new ThreeDayWeatherReport(units);
        report.setTemperatures(temperaturesForDay1, temperaturesForDay2, temperaturesForDay3);
        report.setDays(day1FromNow, day2FromNow, day3FromNow);
        return report;
    }


    public CoordinateReport getCoordinateReport() {
        CoordinateReport report = new CoordinateReport(loopThroughLinesForCoordinate());
        return report;
    }

    public void getWeatherDataAsLines(WeatherRequest request) throws WrongCityNameException {
        String outputFromRequest = request.getJSONFromURL();
        lines = getLinesFromOutput(outputFromRequest);
    }

    public String loopThroughLinesForCoordinate() {
        String coord = "";
        for (String line : lines) {
            try {
                if (latitudeIsAtCurrent(line)) {
                    coord = coord.concat(getLatitudeFrom(line));
                } else if (longitudeIsAtCurrent(line)) {
                    coord = coord.concat(getLongitudeFrom(line));
                }
            } catch (StringIndexOutOfBoundsException e) {
                // not important line
            }
        }
        return coord;
    }

    private boolean longitudeIsAtCurrent(String line) {
        return line.substring(1, 4).equals("lon");
    }

    private boolean latitudeIsAtCurrent(String line) {
        return line.substring(1, 6).equals("coord");
    }

    private String getLongitudeFrom(String line) {
        String y = line.substring(6, line.length() - 1);
        if (y.charAt(0) == '-') {
            return ":" + y.substring(0, 5);
        } else {
            return ":" + y.substring(0, 4);
        }

    }

    private String getLatitudeFrom(String line) {
        String x = line.substring(15);
        if (x.charAt(0) == '-') {
            return x.substring(0, 5);
        } else {
            return x.substring(0, 4);
        }
    }

    public String loopThroughLinesForFirstTemperature() {
        String temperature = "";
        for (String line : lines) {
            try {
                if (line.substring(9, 13).equals("temp")) {
                    temperature = line.substring(15);
                }
            } catch (StringIndexOutOfBoundsException e) {
                // occurs only with lines we don't need
            }
        }
        return temperature;
    }


    public void loopThroughLinesForThreeDayTemperatures() {
        String day = null;
        for (String line : lines) {
            try {
                String newDay = checkLineForDay(line);
                if (newDay != null) {
                    day = newDay;
                }
                if (day != null) {
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

    public void getNextThreeDaysFrom(Date date) {
        day1FromNow = getNthDayFrom(date,1);
        day2FromNow = getNthDayFrom(date,2);
        day3FromNow = getNthDayFrom(date,3);
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC +02:00"));
        return dateFormat.format(date);
    }

    public String getNthDayFrom(Date date, int n) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC +02:00"));
        date = addNDaysToDate(date, n);
        return dateFormat.format(date);
    }

    public Date addNDaysToDate(Date date, int daysToAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, daysToAdd);
        return calendar.getTime();
    }


    public List<Double> getTemperaturesForDay3() {
        return temperaturesForDay3;
    }

    public List<Double> getTemperaturesForDay1() {
        return temperaturesForDay1;
    }

    public List<Double> getTemperaturesForDay2() {
        return temperaturesForDay2;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}
