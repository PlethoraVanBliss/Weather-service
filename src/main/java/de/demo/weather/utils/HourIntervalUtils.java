package de.demo.weather.utils;

import de.demo.weather.config.DayIntervalProperties.DayInterval;
import de.demo.weather.config.DayIntervalProperties.HourInterval;
import de.demo.weather.dto.EpochTimePeriod;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.validation.ClockProvider;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static java.time.Duration.ZERO;
import static java.time.Instant.ofEpochMilli;
import static java.time.LocalDate.now;

@Component
public class HourIntervalUtils {

    private static final int THOUSAND = 1_000;

    private final ClockProvider clockProvider;

    public HourIntervalUtils(ClockProvider clockProvider) {
        this.clockProvider = clockProvider;
    }

    @Nonnull
    public EpochTimePeriod getEpochInterval(@Nonnull final HourInterval hourInterval,
                                                   @Nonnull final DayInterval dayInterval) {
        long startDate = dayInterval.getStartDay().toMillis() + dateToEpochMilli(now(clockProvider.getClock())) ;
        long startTime = startDate + hourInterval.getStart().toMillis();
        long endDate = dayInterval.getStartDay().plus(hourInterval.getEnd()).toMillis()+ dateToEpochMilli(now(clockProvider.getClock()));
        long endTime = endDate + dayInterval.getNumberOfDays().toMillis();
        return EpochTimePeriod.builder()
                .start(startTime/ THOUSAND)
                .dayStart(startDate / THOUSAND)
                .end(endTime/ THOUSAND).build();
    }


    @Nonnull
    public EpochTimePeriod getAllDayEpochInterval(@Nonnull final DayInterval dayInterval) {
        HourInterval hourInterval = new HourInterval(ZERO, ZERO);
        return getEpochInterval(hourInterval, dayInterval);
    }

    public LocalDateTime epochToDateTime(long epochSeconds) {
        return LocalDateTime.ofInstant(ofEpochMilli(epochSeconds * THOUSAND), ZoneId.of("UTC"));
    }

    private long dateToEpochMilli(@Nonnull LocalDate date) {
        return date.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}
