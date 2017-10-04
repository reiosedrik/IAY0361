import org.junit.Before;
import org.junit.Test;
import request.WeatherRequest;

import static junit.framework.TestCase.assertFalse;

public class testRequest {

    private WeatherRequest weatherRequest;

    @Before
    public void setupWeatherRequest() {
        weatherRequest = new WeatherRequest("Tallinn", "metric");
    }

    @Test
    public void testResponseNotNull() {
        assertFalse(weatherRequest.getJSONFromURL() == null);
    }
}
