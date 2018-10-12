package de.demo.weather.service.delegate;

 import de.demo.weather.constant.RequestType;

 import javax.annotation.Nonnull;

public interface AverageForecastDelegate {

    short getAverageForecast(@Nonnull final String city);

    RequestType supports();

}
