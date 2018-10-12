package de.demo.weather.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationErrorMessageDTO {

    private final String field;
    private final String defaultMessage;
    private final Object rejectValue;
    private final String code;
}
