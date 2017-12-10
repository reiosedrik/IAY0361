package report;

public class CoordinateReport {

    private String coordinate;
//    private String city;

    public CoordinateReport(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getInfo() {
        return String.format("Coordinates: %s", coordinate);
    }

//    public void setCity(String city) {
//        this.city = city;
//    }
}
