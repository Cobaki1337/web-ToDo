package by.gsu.epamlab.controller.exceptions;

public class DAOException extends Exception {
    public DAOException(Throwable cause) {
        super(cause);
    }
    public DAOException(String message) {
        super(message);
    }
}
