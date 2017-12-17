package report;

import exceptions.WrongCityNameException;
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
    public void makeRequest() throws WrongCityNameException {
        request = new WeatherRequest("Tallinn", "metric");
        repository = new WeatherRepository(request);
    }


    @Test
    public void testIfReportReturnsTemperaturesForThreeDays() {
        ThreeDayWeatherReport report = repository.getThreeDayWeatherReport();
        List<String> days = report.threeDays;
        assertEquals(days.size(), 3);
    }

    @Test
    public void testIfHighestTemperatureISHigherThanLowestDay2() {
        ThreeDayWeatherReport report = repository.getThreeDayWeatherReport();
        report.getInfo();
        double highest = Double.parseDouble(report.day2Highest.split("\u00b0")[0]);
        double lowest = Double.parseDouble(report.day2Lowest.split("\u00b0")[0]);
        assertTrue(lowest < highest);
    }

    @Test
    public void testIfHighestTemperatureISHigherThanLowestDay1() {
        ThreeDayWeatherReport report = repository.getThreeDayWeatherReport();
        report.getInfo();
        double highest = Double.parseDouble(report.day1Highest.split("\u00b0")[0]);
        double lowest = Double.parseDouble(report.day1Lowest.split("\u00b0")[0]);
        assertTrue(lowest < highest);
    }

}
