package report;

import exceptions.WrongCityNameException;
import org.junit.Test;
import report.CurrentWeatherReport;
import report.ThreeDayWeatherReport;
import repository.WeatherRepository;
import request.WeatherRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testCurrentWeatherReport {

    @Test
    public void testIfTemperatureIsRealisticMetric() throws WrongCityNameException {
        WeatherRequest request = new WeatherRequest("Tallinn", "metric");
        WeatherRepository repository = new WeatherRepository(request);
        CurrentWeatherReport report = repository.getCurrentWeatherReport();
        assertTrue(report.getCurrentTemperature() > -60 && report.getCurrentTemperature() < 60);
    }

    @Test
    public void testIfTemperatureIsRealisticImperial() throws WrongCityNameException {
        WeatherRequest request = new WeatherRequest("Paris", "imperial");
        WeatherRepository repository = new WeatherRepository(request);
        CurrentWeatherReport report = repository.getCurrentWeatherReport();
        assertTrue(report.getCurrentTemperature() > -60 && report.getCurrentTemperature() < 60);
    }

    @Test
    public void testFormatImperial() throws WrongCityNameException {
        WeatherRequest request = new WeatherRequest("Paris", "imperial");
        WeatherRepository repository = new WeatherRepository(request);
        CurrentWeatherReport report = repository.getCurrentWeatherReport();
        report.setCurrentTemperature(35);
        assertEquals("Current temperature: 35.0\u00b0F", report.getInfo());
    }

    @Test
    public void testFormatMetric() throws WrongCityNameException {
        WeatherRequest request = new WeatherRequest("Paris", "metric");
        WeatherRepository repository = new WeatherRepository(request);
        CurrentWeatherReport report = repository.getCurrentWeatherReport();
        report.setCurrentTemperature(-12);
        assertEquals("Current temperature: -12.0\u00b0C", report.getInfo());
    }

}
