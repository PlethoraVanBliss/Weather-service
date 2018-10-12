package de.demo.weather.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EpochTimePeriod {

    private final long start;
    private final long end;
    private final long dayStart;
}
