package de.demo.weather.config;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.HOURS;

@Data
@Component
@ConfigurationProperties(prefix = "de.demo.weather.day-interval")
public class DayIntervalProperties {

    private static final int MINUTE = 0;

    @NotNull
    private HourInterval day;

    @NotNull
    private HourInterval night;

    @NotNull
    private DayInterval dayInterval;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class HourInterval {

        @DurationUnit(HOURS)
        private Duration start;

        @DurationUnit(HOURS)
        private Duration end;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class DayInterval {

        @DurationUnit(HOURS)
        private Duration numberOfDays;

        @DurationUnit(HOURS)
        private Duration startDay;
    }
}
