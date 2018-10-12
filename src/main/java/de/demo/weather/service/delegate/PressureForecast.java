package de.demo.weather.service.delegate;

import de.demo.weather.config.DayIntervalProperties.DayInterval;
import de.demo.weather.constant.RequestType;
import de.demo.weather.dto.EpochTimePeriod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Slf4j
@Component
public class PressureForecast extends AverageForecastDelegateImpl implements AverageForecastDelegate {

    @Override
    @Cacheable(value = "de.demo.weather.pressureforecast")
    public short getAverageForecast(@Nonnull String city) {
        log.info("Retrieving average weather pressure forecast");
        DayInterval daysInterval = dayIntervalProperties.getDayInterval();
        EpochTimePeriod epochInterval = hourIntervalUtils.getAllDayEpochInterval(daysInterval);
        short pressureAverageTemperature =  getAverage(city,epochInterval,
                x->true,
                forecast -> forecast.getMain().getPressure());
        log.info("Found average weather pressure <{}>", pressureAverageTemperature);
        return pressureAverageTemperature;
    }

    @Override
    public RequestType  supports() {
        return RequestType.PRESSURE;
    }
}
