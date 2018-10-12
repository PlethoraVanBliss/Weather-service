package de.demo.weather.service.delegate;

import static de.demo.weather.config.DayIntervalProperties.HourInterval;

import de.demo.weather.constant.RequestType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Slf4j
@Component
public class NightlyForecast extends AverageTemperatureForecastDelegateImpl implements AverageForecastDelegate {

    @Override
    @Cacheable(value = "de.demo.weather.nightlyforecast")
    public short getAverageForecast(@Nonnull String city) {
        log.info("Retrieving average nightly temperature forecast");
        short nightAverageTemperature = getAverageTemperatureForecast(city, getTimeSlotExclusionCondition().negate());
        log.info("Found average nightly temperature <{}>", nightAverageTemperature);
        return nightAverageTemperature;
    }

    @Nonnull
    public HourInterval getHourInterval() {
        return dayIntervalProperties.getNight();
    }

    @Override
    public RequestType supports() {
        return RequestType.NIGHTLY;
    }
}
