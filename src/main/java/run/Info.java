package run;

import exceptions.InvalidUnitsException;
import report.CoordinateReport;
import report.CurrentWeatherReport;
import report.ThreeDayWeatherReport;
import repository.WeatherRepository;
import request.WeatherRequest;

import java.io.*;
import java.nio.file.Paths;

public class Info {

    private String path;
    private String units = "metric";
    private String city;
    private WeatherRepository repo;
    private ThreeDayWeatherReport report3Day;
    private CurrentWeatherReport reportCurrent;
    private CoordinateReport reportCoords;

    public Info() {
        createPath();
    }

    public void readCitiesAndWriteInfoToSeparateFiles() {
        String city;
        try {
            FileReader in = new FileReader(
                    path + "input.txt");
            BufferedReader reader = new BufferedReader(in);
            while ((city = reader.readLine()) != null && city.length() != 0) {
                this.city = city;
                BufferedWriter writer = new BufferedWriter(new FileWriter(
                        path + city + ".txt"));
                createReportsFor();
                writeToFile(writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(BufferedWriter writer) throws IOException {
        writer.write(city + "\n");
        writer.write(reportCoords.getInfo());
        writer.write(report3Day.getInfo() + "\n");
        writer.write(reportCurrent.getInfo());
        writer.close();
    }

    private void createReportsFor() {
        WeatherRequest request = new WeatherRequest(city, units);
        repo = new WeatherRepository(units);
        report3Day = repo.getThreeDayWeatherReport(request);
        reportCurrent = repo.getCurrentWeatherReport(request);
        reportCoords = repo.getCoordinateReport(request);
    }

    private void createPath() {
        path = Paths.get("").toAbsolutePath() + "/src/main/java/cities/";
    }

    public void setUnits(String units) throws InvalidUnitsException {
        if (units.toLowerCase().equals("metric") || units.toLowerCase().equals("imperial")) {
            this.units = units;
        } else {
            throw new InvalidUnitsException("Please enter correct units");
        }
    }

}

