package run;

import cities.Cities;

public class GetWeatherInfo {

    public static void main(String[] args) {
        Cities.addFromConsole();
        Info info = new Info();
//        info.setUnits("imperial");
        info.readCitiesAndWriteInfoToSeparateFiles();
    }
}
