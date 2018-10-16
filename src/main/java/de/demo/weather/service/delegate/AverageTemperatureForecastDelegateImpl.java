package de.demo.weather.service.delegate;

import de.demo.weather.client.OpenWeatherResponseDetailsDTO;

import static de.demo.weather.config.DayIntervalProperties.*;
import static de.demo.weather.constant.MetricsEnum.KELVIN_TO_CELSIUS;

import de.demo.weather.dto.EpochTimePeriod;
import de.demo.weather.service.MetricConversionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

@Slf4j
@Component
abstract class AverageTemperatureForecastDelegateImpl extends AverageForecastDelegateImpl {

    @Autowired
    private MetricConversionService metricConversionService;

    short getAverageTemperatureForecast(@Nonnull String city,
                                       @Nonnull Predicate<Pair<OpenWeatherResponseDetailsDTO, Integer>> timeSlotExclusionCondition) {

        log.trace("About to calculate the average temperature ");
        return metricConversionService.convert(getAverage(city, getEpochInterval(),
                timeSlotExclusionCondition,
                forecast -> forecast.getMain().getTemp()), KELVIN_TO_CELSIUS);
    }

    abstract HourInterval getHourInterval();

    Predicate<Pair<OpenWeatherResponseDetailsDTO, Integer>> getTimeSlotExclusionCondition() {

        return forecast -> forecast.getRight() >= getHourInterval().getStart().toHours() &&
                forecast.getRight() <= getHourInterval().getEnd().toHours();
    }

    @Nonnull
    private EpochTimePeriod getEpochInterval() {
        return hourIntervalUtils.getEpochInterval(getHourInterval(), dayIntervalProperties.getDayInterval());
    }

}
