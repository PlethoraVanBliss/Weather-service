package de.demo.weather.config;

import de.demo.weather.constant.MetricsEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.util.EnumMap;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "de.demo.weather.metrics")
public class MetricsProperties {

    @NotEmpty
    private Map<MetricsEnum, Double> conversion = new EnumMap<>(MetricsEnum.class);
}
