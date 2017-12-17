package getresults;

import exceptions.InvalidUnitsException;
import exceptions.WrongCityNameException;
import report.CoordinateReport;
import report.CurrentWeatherReport;
import report.ThreeDayWeatherReport;
import repository.WeatherRepository;
import request.WeatherRequest;

import java.io.*;
import java.nio.file.Paths;

public class Data {

    private String path;
    private String units = "metric";
    private String city;
    private WeatherRepository repo;
    private ThreeDayWeatherReport report3Day;
    private CurrentWeatherReport reportCurrent;
    private CoordinateReport reportCoords;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Data() throws FileNotFoundException {
        createPath();
        reader = createReader();
    }

    public void readCitiesAndWriteInfoToSeparateFiles() throws WrongCityNameException {
        String city;
        try {
            while ((city = reader.readLine()) != null && city.length() != 0) {
                this.city = city;
                BufferedWriter writer = new BufferedWriter(new FileWriter(
                        path + city + ".txt"));
                createReportsForCity();
                writeToFile(writer, createStringToWriteToFile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedReader createReader() throws FileNotFoundException {
        FileReader in = new FileReader(
                path + "input.txt");
        return new BufferedReader(in);
    }

    public void writeToFile(BufferedWriter writer, String s) throws IOException {
        writer.write(s);
        writer.close();
    }

    public String createStringToWriteToFile() {
        return String.format("%s\n%s%s\n%s",
                city, reportCoords.getInfo(), report3Day.getInfo(), reportCurrent.getInfo());
    }

    private void createReportsForCity()  throws WrongCityNameException {
        WeatherRequest request = new WeatherRequest(city, units);
        repo = new WeatherRepository(request);
        report3Day = repo.getThreeDayWeatherReport();
        reportCurrent = repo.getCurrentWeatherReport();
        reportCoords = repo.getCoordinateReport();
    }

    private void createPath() {
        path = Paths.get("").toAbsolutePath() + "/src/main/java/cities/";
    }

    public void setUnits(String units) throws InvalidUnitsException {
        if (units.toLowerCase().equals("metric") || units.toLowerCase().equals("imperial")) {
            this.units = units.toLowerCase();
        } else {
            throw new InvalidUnitsException("Please enter correct units");
        }
    }

    public String getUnits() {
        return units;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setReport3Day(ThreeDayWeatherReport report3Day) {
        this.report3Day = report3Day;
    }

    public void setReportCurrent(CurrentWeatherReport reportCurrent) {
        this.reportCurrent = reportCurrent;
    }

    public void setReportCoords(CoordinateReport reportCoords) {
        this.reportCoords = reportCoords;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }
}

