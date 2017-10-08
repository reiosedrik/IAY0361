package report;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import report.ThreeDayWeatherReport;
import repository.WeatherRepository;
import request.WeatherRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testThreeDayWeatherReport {

    WeatherRequest request;
    WeatherRepository repository;

    @Before
    public void makeRequest() {
        request = new WeatherRequest("Tallinn", "metric");
        repository = new WeatherRepository();
    }

    @Test
    public void testIfCityEqualsRequestCity() {
        WeatherRequest request = new WeatherRequest("Tallinn", "metric");
        WeatherRepository repository = new WeatherRepository();
        ThreeDayWeatherReport report = repository.getThreeDayWeatherReport(request);
        assertEquals(report.getCity(), "Tallinn");
    }


    @Test
    public void testIfReportReturnsTemperaturesForThreeDays() {
        ThreeDayWeatherReport report = repository.getThreeDayWeatherReport(request);
        List<String> days = report.threeDays;
        assertEquals(days.size(), 3);
    }

    @Test
    public void testIfHighestTemperatureISHigherThanLowestDay2() {
        ThreeDayWeatherReport report = repository.getThreeDayWeatherReport(request);
        report.getInfo();
        double highest = Double.parseDouble(report.day2Highest);
        double lowest = Double.parseDouble(report.day2Lowest);
        assertTrue(lowest < highest);
    }

    @Test
    public void testIfHighestTemperatureISHigherThanLowestDay1() {
        ThreeDayWeatherReport report = repository.getThreeDayWeatherReport(request);
        report.getInfo();
        double highest = Double.parseDouble(report.day1Highest);
        double lowest = Double.parseDouble(report.day1Lowest);
        assertTrue(lowest < highest);
    }

}
