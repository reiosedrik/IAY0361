package report;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testCoordinateReport {

    @Test
    public void testFormatPositive() {
        CoordinateReport r = new CoordinateReport("14.5:54.5");
        assertEquals("Coordinates: 14.5:54.5", r.getInfo());
    }

    @Test
    public void testFormatNegative() {
        CoordinateReport r = new CoordinateReport("-11.0:-24.1");
        assertEquals("Coordinates: -11.0:-24.1", r.getInfo());
    }
}
