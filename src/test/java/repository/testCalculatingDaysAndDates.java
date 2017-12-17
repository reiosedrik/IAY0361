package repository;

import exceptions.WrongCityNameException;
import org.junit.Before;
import org.junit.Test;
import request.WeatherRequest;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class testCalculatingDaysAndDates {

    private WeatherRequest request;
    private WeatherRepository repository;

    @Before
    public void makeRequest() throws WrongCityNameException {
        request = new WeatherRequest("Tallinn", "metric");
        repository = new WeatherRepository(request);
    }

    @Test
    public void testGetDayFromUNIXtuesday() {
        String day = repository.getDayFromUNIX("1507582800");
        assertEquals("Monday", day);
    }

    @Test
    public void testGetDayFromUNIXthursday() {
        String day = repository.getDayFromUNIX("0000000000");
        assertEquals("Thursday", day);
    }

    @Test
    public void testAddNDaysToDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2017, Calendar.JANUARY, 9);
        Date date = cal.getTime();
        cal.setTime(repository.addNDaysToDate(date, 3));
        assertEquals(12, cal.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testAddNegativeNDaysToDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2017, Calendar.JANUARY, 12);
        Date date = cal.getTime();
        cal.setTime(repository.addNDaysToDate(date, -3));
        assertEquals(9, cal.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testAddNDaysToDateNewMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(2017, Calendar.DECEMBER, 29);
        Date date = cal.getTime();
        cal.setTime(repository.addNDaysToDate(date, 5));
        assertEquals(3, cal.get(Calendar.DAY_OF_MONTH));
    }
}
