package de.demo.weather.service.delegate;

import de.demo.weather.config.DayIntervalProperties.HourInterval;
import de.demo.weather.constant.RequestType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Slf4j
@Component
public class DailyForecast extends AverageTemperatureForecastDelegateImpl implements AverageForecastDelegate {

    @Override
    @Cacheable(value = "de.demo.weather.dailyforecast")
    public short getAverageForecast(@Nonnull String city) {
        log.info("Retrieving daily average temperature forecast");
        short dailyAverageTemperature = getAverageTemperatureForecast(city, getTimeSlotExclusionCondition());
        log.info("Found daily average temperature <{}>", dailyAverageTemperature);
        return dailyAverageTemperature;
    }

    @Nonnull
    public HourInterval getHourInterval() {
        return dayIntervalProperties.getDay();
    }

    @Override
    public RequestType supports() {
        return RequestType.DAILY;
    }
}