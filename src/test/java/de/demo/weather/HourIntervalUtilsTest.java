package de.demo.weather;

import de.demo.weather.config.DayIntervalProperties.DayInterval;
import de.demo.weather.config.DayIntervalProperties.HourInterval;
import de.demo.weather.dto.EpochTimePeriod;
import de.demo.weather.utils.HourIntervalUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;

import static de.demo.weather.StubUtils.getClock;
import static java.time.Duration.ofDays;
import static java.time.Duration.ofHours;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HourIntervalUtilsTest {

  private static HourIntervalUtils hourIntervalUtils;
    private static final LocalDateTime CURRENT_DATE = LocalDateTime.of(2018, 10, 12, 23,15);

    @BeforeClass
    public static void init() {
         hourIntervalUtils = new HourIntervalUtils(() -> getClock(CURRENT_DATE));
    }

    @Test
    public void getEpochIntervalForAllDay() {

        DayInterval dayInterval = new DayInterval(ofDays(2), ofHours(24));

        EpochTimePeriod actualEpochInterval = hourIntervalUtils.getAllDayEpochInterval(dayInterval);
        assertThat(actualEpochInterval.getStart(), is(1539388800L));  //2018-10-13T02:00:00.000+02:00
        assertThat(actualEpochInterval.getEnd(), is(1539561600L)); // 2018-10-15T02:00:00.000+02:00
    }

    @Test
    public void getEpochIntervalFrom6To18() {

        DayInterval dayInterval = new DayInterval(ofDays(2), ofHours(24));
        HourInterval hourInterval = new HourInterval(ofHours(6), ofHours(18));
        EpochTimePeriod actualEpochInterval = hourIntervalUtils.getEpochInterval(hourInterval, dayInterval);
        assertThat(actualEpochInterval.getStart(), is(1539410400L));  //2018-10-13T08:00:00.000+02:00
        assertThat(actualEpochInterval.getEnd(), is(1539626400L)); // 2018-10-15T20:00:00.000+02:00
    }
}