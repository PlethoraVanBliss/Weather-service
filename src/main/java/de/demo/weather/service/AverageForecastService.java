package de.demo.weather.service;

import de.demo.weather.constant.RequestType;
import de.demo.weather.dto.AverageForecastDTO;
import de.demo.weather.service.delegate.AverageForecastDelegate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static de.demo.weather.constant.RequestType.PRESSURE;

@Slf4j
@Component
@AllArgsConstructor
public class AverageForecastService {

    private List<AverageForecastDelegate> averageForecastDelegateList;

    @Nonnull
    public AverageForecastDTO getAverage(@Nonnull String city) {

        AtomicReference<AverageForecastDTO.AverageForecastDTOBuilder> builder = new AtomicReference<>();
        builder.set(AverageForecastDTO.builder());
        averageForecastDelegateList.stream()
                .map(x->ImmutablePair.of(x.getAverageForecast(city), x.supports()))
                .forEach(x->x.right.getConsumer().accept( builder.get(), x.left));
        return builder.get().build();
    }
}
