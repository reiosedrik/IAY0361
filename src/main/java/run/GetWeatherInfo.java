package run;

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
        Cities.addFromConsole();
        Info info = new Info();
        info.getAll();
    }
}
