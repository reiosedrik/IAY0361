import org.junit.Test;
import report.ThreeDayWeatherReport;
import repository.WeatherRepository;
import request.WeatherRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testRepository {

    @Test
    public void testIfRepositoryReturnsCurrentWeatherReport() {
        WeatherRequest request = new WeatherRequest("Tallinn", "metric");
        WeatherRepository repository = new WeatherRepository();
        assertTrue(repository.getCurrentWeatherReport(request) != null);
    }

    @Test
    public void testIfRepositoryReturnsThreeDayWeatherReport() {
        WeatherRequest request = new WeatherRequest("Tallinn", "metric");
        WeatherRepository repository = new WeatherRepository();
        assertTrue(repository.getThreeDayWeatherReport(request) != null);
    }


    @Test
    public void testMetricTemeperature() {
        WeatherRequest request = new WeatherRequest("Tallinn", "metric");
        WeatherRepository repository = new WeatherRepository();
        assertEquals(repository.getUnits(), "metric");
    }

    @Test
    public void testImperialTemeperature() {
        WeatherRequest request = new WeatherRequest("Tallinn", "imperial");
        WeatherRepository repository = new WeatherRepository();
        assertEquals(repository.getUnits(), "imperial");
    }
}
