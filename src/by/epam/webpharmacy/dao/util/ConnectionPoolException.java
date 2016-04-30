package by.epam.webpharmacy.dao.util;

public class ConnectionPoolException extends Exception {

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
