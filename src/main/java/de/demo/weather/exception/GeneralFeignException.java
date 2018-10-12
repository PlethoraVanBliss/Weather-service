package de.demo.weather.exception;

import feign.Response;


public class GeneralFeignException extends RuntimeException {

    private final Response response;

    public GeneralFeignException(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
