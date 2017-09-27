import org.junit.Test;
import report.ThreeDayWeatherReport;
import repository.WeatherRepository;
import request.WeatherRequest;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testThreeDayWeatherReport {

    @Test
    public void testIfCityEqualsRequestCity() {
        WeatherRequest request = new WeatherRequest("Tallinn", "metric");
        WeatherRepository repository = new WeatherRepository();
        ThreeDayWeatherReport report = repository.getThreeDayWeatherReport(request);
        assertEquals(report.getCity(), "Tallinn");
    }


    @Test
    public void testIfReportReturnsTemperaturesForThreeDays() {
        WeatherRequest request = new WeatherRequest("Tallinn", "metric");
        WeatherRepository repository = new WeatherRepository();
        ThreeDayWeatherReport report = repository.getThreeDayWeatherReport(request);
        ArrayList<String> days = report.getDays();
        assertEquals(days.size(), 3);
    }

    @Test
    public void testIfHighestTemperatureISHigherThanLowest() {
        WeatherRequest request = new WeatherRequest("Tallinn", "metric");
        WeatherRepository repository = new WeatherRepository();
        ThreeDayWeatherReport report = repository.getThreeDayWeatherReport(request);
        ArrayList<String> days = report.getDays();
        Map<String, Integer> lowestTemperatures = report.getLowestTemperatures();
        Map<String, Integer> highestTemperatures = report.getLowestTemperatures();
        for (String day : days) {
            assertTrue(lowestTemperatures.get(day) < highestTemperatures.get(day));
        }
    }
}
