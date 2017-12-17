package getresults;

import exceptions.InvalidUnitsException;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class testGettingData {

    private Data data;

    @Before
    public void createInfo() throws FileNotFoundException {
        data = new Data();
    }


    @Test(expected = InvalidUnitsException.class)
    public void testSettingInvalidUnits() throws InvalidUnitsException{
        data.setUnits("valed unitid");
    }

    @Test
    public void setUnitsInCaps() throws InvalidUnitsException {
        data.setUnits("METRIC");
        assertEquals("metric", data.getUnits());
    }

    @Test
    public void setUnitsMetric() throws InvalidUnitsException {
        data.setUnits("metric");
        assertEquals("metric", data.getUnits());
    }

    @Test
    public void setUnitsImperial() throws InvalidUnitsException {
        data.setUnits("imperial");
        assertEquals("imperial", data.getUnits());
    }

}
