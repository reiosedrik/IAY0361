package report;

import org.junit.Test;
import report.CurrentWeatherReport;
import report.ThreeDayWeatherReport;
import repository.WeatherRepository;
import request.WeatherRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testCurrentWeatherReport {

    @Test
    public void testIfCityEqualsRequestCity() {
        WeatherRequest request = new WeatherRequest("Tallinn", "metric");
        WeatherRepository repository = new WeatherRepository();
        CurrentWeatherReport report = repository.getCurrentWeatherReport(request);
        assertEquals(report.getCity(), "Tallinn");
    }

    @Test
    public void testIfTemperatureIsRealistic() {
        WeatherRequest request = new WeatherRequest("Tallinn", "metric");
        WeatherRepository repository = new WeatherRepository();
        CurrentWeatherReport report = repository.getCurrentWeatherReport(request);
        assertTrue(report.getCurrentTemperature() > -60 && report.getCurrentTemperature() < 60);
    }

}
