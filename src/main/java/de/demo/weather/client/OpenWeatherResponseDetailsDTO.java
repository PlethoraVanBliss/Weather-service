package de.demo.weather.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

@Data
public class OpenWeatherResponseDetailsDTO {

    private @NonNull Long dt;
    private @NonNull Temperature main;

    @JsonProperty("dt_txt")
    private @NonNull String dtTxt;

    @Data
    public static class Temperature{
        private double temp;
        private double pressure;
    }
}
