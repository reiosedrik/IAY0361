package request;

import exceptions.WrongCityNameException;
import org.junit.Before;
import org.junit.Test;
import request.WeatherRequest;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class testRequest {

    private WeatherRequest weatherRequest;

    @Before
    public void setupWeatherRequest() {
        weatherRequest = new WeatherRequest("Tallinn", "metric");
    }

    @Test
    public void testResponseNotNull() throws WrongCityNameException {
        assertFalse(weatherRequest.getJSONFromURL() == null);
    }

    @Test
    public void testIfCityIsCorrect() {
        assertEquals("Tallinn", weatherRequest.getCity());
    }

    @Test
    public void testIfUnitsAreCorrect() {
        assertEquals("metric", weatherRequest.getUnits());
    }


    @Test
    public void testUrlCreatedWhenCreatingRequest() {
        assertTrue(weatherRequest.getRequestAddress() != null);
    }


}
