package de.demo.weather.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel("The weather average forecast details")
public class AverageForecastDTO {

    @ApiModelProperty("The average of daily(6h-18h) temperature, in celsius")
    private Short day;

    @ApiModelProperty("The average of daily(6h-18h) temperature, in celsius")
    private Short night;

    @ApiModelProperty("The average atmospheric pressure represented in hPA")
    private Short pressure;
}
