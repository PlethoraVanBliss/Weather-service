package de.demo.weather.constant;

import de.demo.weather.dto.AverageForecastDTO;

import java.util.function.BiConsumer;

public enum RequestType {
    DAILY(AverageForecastDTO.AverageForecastDTOBuilder::day),
    NIGHTLY(AverageForecastDTO.AverageForecastDTOBuilder::night),
    PRESSURE(AverageForecastDTO.AverageForecastDTOBuilder::pressure);

    RequestType(BiConsumer<AverageForecastDTO.AverageForecastDTOBuilder, Short> consumer) {
        this.consumer = consumer;
    }

    public BiConsumer<AverageForecastDTO.AverageForecastDTOBuilder, Short> getConsumer() {
        return consumer;
    }

    private BiConsumer<AverageForecastDTO.AverageForecastDTOBuilder, Short> consumer;
}
