package de.demo.weather.api;

import de.demo.weather.dto.AverageForecastDTO;
import de.demo.weather.service.AverageForecastService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

import static de.demo.weather.constant.ValidationPattern.ALPHA_ONLY;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/v1/forecast")
public class AverageForecastController {

    private final AverageForecastService averageForecastService;

    @RequestMapping(value = "/{city}", method = GET, produces = APPLICATION_JSON_VALUE)
    @ApiOperation("View list of weather average temperatures for the following 3 days")
    public AverageForecastDTO getForecast(@Pattern(regexp = ALPHA_ONLY, message = "City name must not contain commas or numbers")
                                          @PathVariable String city) {
        log.info("Getting average temperature for city [{}]", city);
        AverageForecastDTO result = averageForecastService.getAverage(city);
        log.trace("AverageForecastDTO result {} ", result);
        return result;
    }
}
