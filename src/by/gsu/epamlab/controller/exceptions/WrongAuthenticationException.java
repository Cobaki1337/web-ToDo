package by.gsu.epamlab.controller.exceptions;

public class WrongAuthenticationException extends DAOException {
    public WrongAuthenticationException(String message) {
        super(message);
    }
}
