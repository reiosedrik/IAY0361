package exceptions;

public class WrongCityNameException extends Exception {
    public WrongCityNameException() {
    }

    public WrongCityNameException(String message) {
        super(message);
    }
}