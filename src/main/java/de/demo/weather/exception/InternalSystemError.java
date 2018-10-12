package de.demo.weather.exception;

public class InternalSystemError extends RuntimeException {
    public InternalSystemError(String message) {
        super(message);
    }

    public InternalSystemError(String message, Throwable cause) {
        super(message, cause);
    }
}
