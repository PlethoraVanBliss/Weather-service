package de.demo.weather.service;

import de.demo.weather.config.MetricsProperties;
import de.demo.weather.constant.MetricsEnum;
import de.demo.weather.exception.InternalSystemError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

import static de.demo.weather.utils.RoundingUtils.*;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Component
@AllArgsConstructor
public class MetricConversionService {

    private final MetricsProperties metricsProperties;

    public byte convert(short metric,
                        @Nonnull MetricsEnum metricName) {
        return ofNullable(metricsProperties.getConversion().get(metricName))
                .map(m -> round(metric + m))
                .map(BigDecimal::byteValue)
                .orElseThrow(() ->
                        new InternalSystemError(format("No settings/property found for metric conversion [%s]", metricName)));
    }
}
