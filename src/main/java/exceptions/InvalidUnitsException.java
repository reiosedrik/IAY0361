package exceptions;

public class InvalidUnitsException extends Exception {
    public InvalidUnitsException() {
    }

    public InvalidUnitsException(String message) {
        super(message);
    }
}