package repository;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import report.ThreeDayWeatherReport;
import repository.WeatherRepository;
import request.WeatherRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testRepository {

    private WeatherRequest request;
    private WeatherRepository repository;

    @Before
    public void makeRequest() {
        request = new WeatherRequest("Tallinn", "metric");
        repository = new WeatherRepository();
    }

    @Test
    public void testIfRepositoryReturnsCurrentWeatherReport() {
        assertTrue(repository.getCurrentWeatherReport(request) != null);
    }

    @Test
    public void testIfRepositoryReturnsThreeDayWeatherReport() {
        assertTrue(repository.getThreeDayWeatherReport(request) != null);
    }

    @Test
    public void testLoopThroughLinesForFirstTemperature() {
        repository.getCurrentWeatherReport(request);
        try {
            Float.parseFloat(repository.loopThroughLinesForFirstTemperature());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void testLoopThroughLinesForThreeDays() {
        repository.getWeatherDataAsLines(request);
        repository.loopThroughLinesForThreeDays();
        repository.loopThroughLinesForThreeDays();
        assertEquals(4, repository.getTemperaturesForDay3().size());
    }

    @Test
    public void testCheckLineWithDayForDay() {
        String day = repository.checkLineForDay("\"list\":[{\"dt\":1507453200");
        assertEquals("pühapäev", day);
    }

    @Test
    public void testCheckLineWithNoDayForDay() {
        String day = repository.checkLineForDay("\"pressure\":1008.37");
        assertEquals(null, day);
    }

    @Test
    public void testGetDayFromUNIXtuesday() {
        String day = repository.getDayFromUNIX("1507582800");
        assertEquals("esmaspäev", day);
    }

    @Test
    public void testGetDayFromUNIXthursday() {
        String day = repository.getDayFromUNIX("0000000000");
        assertEquals("neljapäev", day);
    }
}
