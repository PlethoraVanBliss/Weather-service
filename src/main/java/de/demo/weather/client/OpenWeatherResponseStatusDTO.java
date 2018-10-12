package de.demo.weather.client;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class OpenWeatherResponseStatusDTO {

    private @NonNull String cod;
    private @NonNull String message;
}
