package de.demo.weather.service;

import de.demo.weather.config.MetricsProperties;
import de.demo.weather.constant.MetricsEnum;
import de.demo.weather.exception.InternalSystemError;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Component
@AllArgsConstructor
public class MetricConversionService {

    private final MetricsProperties metricsProperties;

    public byte convert(@NonNull final short metric, @NonNull MetricsEnum metricName) {
        return ofNullable(metricsProperties.getConversion().get(metricName))
                .map(m -> new BigDecimal(metric + m).byteValue())
                .orElseThrow(() ->
                        new InternalSystemError(format("No settings/property found for metric conversion [%s]", metricName)));
    }
}
