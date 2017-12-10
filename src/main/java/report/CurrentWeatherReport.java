package report;


public class CurrentWeatherReport {

    private double currentTemperature;
    private String temperatureSymbol;


    public CurrentWeatherReport(String units) {
        if (units.equals("imperial")) {
            temperatureSymbol = "\u00b0F";
        } else {
            temperatureSymbol = "\u00b0C";
        }
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public String getInfo() {
        return String.format("Current temperature: %s", Double.toString(currentTemperature) + temperatureSymbol);
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }
}
