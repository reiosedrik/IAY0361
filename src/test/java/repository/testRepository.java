package repository;

import exceptions.WrongCityNameException;
import org.junit.Before;
import org.junit.Test;
import request.WeatherRequest;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testRepository {

    private WeatherRequest request;
    private WeatherRepository repository;

    @Before
    public void makeRequest() throws WrongCityNameException {
        request = new WeatherRequest("Tallinn", "metric");
        repository = new WeatherRepository(request);
    }

    @Test
    public void testIfRepositoryReturnsCurrentWeatherReport() {
        assertTrue(repository.getCurrentWeatherReport() != null);
    }

    @Test
    public void testIfRepositoryReturnsThreeDayWeatherReport() {
        assertTrue(repository.getThreeDayWeatherReport() != null);
    }

    @Test
    public void testLoopThroughLinesForFirstTemperature() {
        repository.getCurrentWeatherReport();
        try {
            Float.parseFloat(repository.loopThroughLinesForFirstTemperature());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGettingDay3Temperatures() throws WrongCityNameException {
        repository.getNextThreeDaysFrom(new Date());
        repository.getWeatherDataAsLines(request);
        repository.loopThroughLinesForThreeDayTemperatures();
        assertEquals(8, repository.getTemperaturesForDay3().size());
    }

    @Test
    public void testGettingDay2Temperatures() throws WrongCityNameException {
        repository.getNextThreeDaysFrom(new Date());
        repository.getWeatherDataAsLines(request);
        repository.loopThroughLinesForThreeDayTemperatures();
        assertEquals(8, repository.getTemperaturesForDay2().size());
    }

    @Test
    public void testGettingDay1Temperatures() throws WrongCityNameException {
        repository.getNextThreeDaysFrom(new Date());
        repository.getWeatherDataAsLines(request);
        repository.loopThroughLinesForThreeDayTemperatures();
        assertEquals(8, repository.getTemperaturesForDay1().size());
    }

    @Test
    public void testCheckLineForDayForDay() {
        String day = repository.checkLineForDay("\"list\":[{\"dt\":1507453200");
        assertEquals("Sunday", day);
    }

    @Test
    public void testCheckLineForNoDayForDay() {
        String day = repository.checkLineForDay("\"pressure\":1008.37");
        assertEquals(null, day);
    }

    @Test
    public void testGetLinesFromOutPut() {
        assertEquals(Arrays.asList("esimene", "teine", "kolmas", "neljas"),
                repository.getLinesFromOutput("esimene,teine,kolmas,neljas"));
    }

    @Test
    public void testGettingTemperatureFromLine() {
        assertTrue(27.52 == repository.getTemperatureFromLine("\"main\":{\"temp\":27.52"));
    }
}
