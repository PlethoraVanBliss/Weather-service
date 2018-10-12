package de.demo.weather.exception;

public class OpenWeatherApiNotAuthorizedException extends RuntimeException {

    public OpenWeatherApiNotAuthorizedException(String message) {
        super(message);
    }

    public OpenWeatherApiNotAuthorizedException(Throwable cause) {
        super(cause);
    }
}
