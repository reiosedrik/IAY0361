package getresults;

import cities.Cities;
import exceptions.InvalidUnitsException;
import exceptions.WrongCityNameException;

import java.io.FileNotFoundException;

public class GetWeatherInfo {

    public static void main(String[] args) throws WrongCityNameException, FileNotFoundException, InvalidUnitsException {
        Cities.addFromConsole();
        Data data = new Data();
        data.readCitiesAndWriteInfoToSeparateFiles();
    }
}
