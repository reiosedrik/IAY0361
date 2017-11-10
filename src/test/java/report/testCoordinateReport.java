package report;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testCoordinateReport {

    @Test
    public void testFormat() {
        CoordinateReport r = new CoordinateReport("14:54");
        r.setCity("paris");
        assertEquals("paris: Koordinaadid on: 14:54", r.getInfo());
    }
}
