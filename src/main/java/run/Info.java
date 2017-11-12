package run;

import report.CoordinateReport;
import report.CurrentWeatherReport;
import report.ThreeDayWeatherReport;
import repository.WeatherRepository;
import request.WeatherRequest;

import java.io.*;

public class Info {

    public void getAll() {
        String city;
        try {
            FileReader in = new FileReader(
                    "C:/Users/Dell/Documents/GitHub/IAY0361/src/main/java/cities/input.txt");
            BufferedReader reader = new BufferedReader(in);
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    "C:/Users/Dell/Documents/GitHub/IAY0361/src/main/java/cities/output.txt"));
            while ((city = reader.readLine()) != null && city.length() != 0) {
                WeatherRequest request = new WeatherRequest(city, "metric");
                WeatherRepository repository = new WeatherRepository();
                ThreeDayWeatherReport report3day = repository.getThreeDayWeatherReport(request);
                CurrentWeatherReport report1day = repository.getCurrentWeatherReport(request);
                CoordinateReport reportcoord = repository.getCoordinateReport(request);
                writer.write(report3day.getInfo());
                writer.newLine();
                writer.write(report1day.getInfo());
                writer.newLine();
                writer.write(reportcoord.getInfo());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

