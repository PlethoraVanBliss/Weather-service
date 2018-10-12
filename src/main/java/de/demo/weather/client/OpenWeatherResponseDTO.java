package de.demo.weather.client;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import static java.util.Collections.emptyList;

@Data
@EqualsAndHashCode(callSuper = true)
public class OpenWeatherResponseDTO extends OpenWeatherResponseStatusDTO {

    private List<OpenWeatherResponseDetailsDTO> list = emptyList();
}
