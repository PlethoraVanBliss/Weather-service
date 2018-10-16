package de.demo.weather.service.delegate;

import de.demo.weather.client.OpenWeatherMapService;
import de.demo.weather.client.OpenWeatherResponseDetailsDTO;
import de.demo.weather.config.DayIntervalProperties;
import de.demo.weather.dto.EpochTimePeriod;
import de.demo.weather.utils.HourIntervalUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.OptionalDouble;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

import static de.demo.weather.utils.RoundingUtils.*;

@Slf4j
@Component
abstract class AverageForecastDelegateImpl {

    @Autowired
    DayIntervalProperties dayIntervalProperties;

    @Autowired
    private OpenWeatherMapService openWeatherMapService;

    @Autowired
    HourIntervalUtils hourIntervalUtils;

    short getAverage(@Nonnull String city,
                    @Nonnull final EpochTimePeriod epochTimePeriod,
                    @Nonnull Predicate<Pair<OpenWeatherResponseDetailsDTO, Integer>> timeSlotCondition,
                    @NonNull ToDoubleFunction<OpenWeatherResponseDetailsDTO> variableToProcess) {
        OptionalDouble averageForecast = calculateAverageForecast(city, epochTimePeriod, timeSlotCondition, variableToProcess);
        AtomicReference<Short> result = new AtomicReference<>();
        averageForecast.ifPresent(avgTemp -> result.set(round(avgTemp).shortValue()));
        averageForecast.orElseThrow(() -> new IllegalArgumentException("No average forecast"));
        return result.get();
    }

    private OptionalDouble calculateAverageForecast(@Nonnull String city,
                                                    @Nonnull final EpochTimePeriod epochTimePeriod,
                                                    @Nonnull Predicate<Pair<OpenWeatherResponseDetailsDTO, Integer>> timeSlotCondition,
                                                    @NonNull ToDoubleFunction<OpenWeatherResponseDetailsDTO> variableToProcess) {
        log.info("Calculating average for city [{}] with epoch time period {}", city, epochTimePeriod);

        return  openWeatherMapService.getForecast(city)
                .getList()
                .stream()
                .filter(forecast -> epochTimePeriod.getStart() <= forecast.getDt())
                .filter(forecast -> epochTimePeriod.getEnd() >= forecast.getDt())
                .map(forecast-> ImmutablePair.of(forecast, hourIntervalUtils.epochToDateTime(forecast.getDt()).getHour()))
                .filter(timeSlotCondition)
                .map(x->x.left)
                .mapToDouble(variableToProcess)
                .average();
    }
}
