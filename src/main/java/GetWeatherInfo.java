import cities.Cities;
import report.CoordinateReport;
import report.CurrentWeatherReport;
import report.ThreeDayWeatherReport;
import repository.WeatherRepository;
import request.WeatherRequest;

import java.io.*;

public class GetWeatherInfo {

    /**
     * Current temperature.
     * From 3 day forecast - highest and lowest for each day.
     * Coordinates (GEO) xxx:yyy.
     */

    public static void main(String[] args) {
        String city = "Paris";
//        Cities.addFromConsole();
        try {
            FileReader in = new FileReader(
                    "C:/Users/Dell/Documents/GitHub/IAY0361/src/main/java/cities/input.txt");
            BufferedReader reader = new BufferedReader(in);
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    "C:/Users/Dell/Documents/GitHub/IAY0361/src/main/java/cities/output.txt"));
            while ((city = reader.readLine()) != null) {
                WeatherRequest request = new WeatherRequest(city, "metric");
                WeatherRepository repository = new WeatherRepository();
                ThreeDayWeatherReport report3day = repository.getThreeDayWeatherReport(request);
                CurrentWeatherReport report1day = repository.getCurrentWeatherReport(request);
                CoordinateReport reportcoord = repository.getCoordinateReport(request);
                System.out.println(report3day.getInfo());
                System.out.println(report1day.getInfo());
                System.out.println(reportcoord.getInfo());

//                writer.write(report1day.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        WeatherRequest request = new WeatherRequest(city, "metric");
        WeatherRepository repository = new WeatherRepository();
//        ThreeDayWeatherReport report3day = repository.getThreeDayWeatherReport(request);
        CurrentWeatherReport report1day = repository.getCurrentWeatherReport(request);
//        CoordinateReport reportcoord = repository.getCoordinateReport(request);
        System.out.println(report1day.getInfo());
//        System.out.println(report3day.getInfo());
//        System.out.println(reportcoord.getInfo());

    }
}
