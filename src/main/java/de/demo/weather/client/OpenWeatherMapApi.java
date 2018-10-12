package de.demo.weather.client;

import de.demo.weather.config.OpenWeatherFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Nonnull;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(value = "openweathermap", url = "${openweathermap.url}", configuration = OpenWeatherFeignConfiguration.class)
public interface OpenWeatherMapApi {

    @RequestMapping(value = "/data/2.5/forecast", method = GET)
    OpenWeatherResponseDTO getForecast(@RequestParam("q") @Nonnull String city);
}
