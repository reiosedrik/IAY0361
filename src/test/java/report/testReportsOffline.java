package report;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.WeatherRepository;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class testReportsOffline {

    BufferedReader reader = new BufferedReader(new FileReader(
            Paths.get("").toAbsolutePath() +"/src/test/java/report/exampleLines"));
    List<String> lines = new ArrayList<>();

    @Before
    public void readLinesFromFile() throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
    }

    @After
    public void emptyLines() {
        lines.clear();
    }

    public testReportsOffline() throws FileNotFoundException {
    }

    @Test
    public void testCreatingCorrectCurrentWeatherReport() {
        WeatherRepository repository = new WeatherRepository();
        repository.setLines(lines);
        repository.setUnits("metric");
        assertEquals("Current temperature: 23.39°C", repository.getCurrentWeatherReport().getInfo());
    }

    @Test
    public void testCreatingCorrectThreeDayWeatherReport() {
        WeatherRepository repository = new WeatherRepository();
        repository.setLines(lines);
        repository.setUnits("metric");
        Calendar cal = Calendar.getInstance();
        cal.set(2017, Calendar.DECEMBER, 13);
        repository.setCurrentDate(cal.getTime());
        ThreeDayWeatherReport report = repository.getThreeDayWeatherReport();

        assertEquals("\nThursday: min temp: 12.2°C, max temp: 27.64°C.\n" +
                "Friday: min temp: 12.64°C, max temp: 24.69°C.\n" +
                "Saturday: min temp: 13.3°C, max temp: 24.75°C.", report.getInfo());
    }

    @Test
    public void testCreatingCorrectCoordinateReport() {
        WeatherRepository repository = new WeatherRepository();
        repository.setLines(lines);
        repository.setUnits("metric");
        assertEquals("Coordinates: -37.8:144.", repository.getCoordinateReport().getInfo());
    }
}
