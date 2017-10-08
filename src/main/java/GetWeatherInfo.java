import report.CurrentWeatherReport;
import report.ThreeDayWeatherReport;
import repository.WeatherRepository;
import request.WeatherRequest;

public class GetWeatherInfo {

    /**
     * Current temperature.
     * From 3 day forecast - highest and lowest for each day.
     * Coordinates (GEO) xxx:yyy.
     */

    public static void main(String[] args) {
        WeatherRequest request = new WeatherRequest("Tallinn", "metric");
        WeatherRepository repository = new WeatherRepository();
        ThreeDayWeatherReport report = repository.getThreeDayWeatherReport(request);
//        CurrentWeatherReport report = repository.getCurrentWeatherReport(request);
        System.out.println(report.getInfo());

    }
}
