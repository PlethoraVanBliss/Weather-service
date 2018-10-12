package de.demo.weather.feign;

import de.demo.weather.exception.GeneralFeignException;
import de.demo.weather.exception.NotFoundException;
import de.demo.weather.exception.OpenWeatherApiNotAuthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class OpenWeatherErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()) {
            case 401:
                return new OpenWeatherApiNotAuthorizedException(methodKey);
            case 404:
                return new NotFoundException("No city found");
            default:
                return new GeneralFeignException(response);
        }
    }
}
