package de.demo.weather.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Slf4j
@Service
 public class OpenWeatherMapService {

    @Autowired
    private OpenWeatherMapApi openWeatherMapApi;

    @Cacheable(value = "de.demo.weather.forecast", sync = true)
    public OpenWeatherResponseDTO getForecast(@NotNull String city){

        log.info("Retrieving forecast for city [{}]", city);
        OpenWeatherResponseDTO forecastData =  openWeatherMapApi.getForecast(city);
        log.trace("Forecast data found {} ", forecastData);
        return forecastData;
    }

}
