package de.demo.weather.config;

import de.demo.weather.config.feign.ApiKeyRequestInterceptor;
import de.demo.weather.feign.OpenWeatherErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenWeatherFeignConfiguration {

    @Bean
    public ApiKeyRequestInterceptor bearerHeaderAuthRequestInterceptor(@Value("${openweathermap.api.key}")
                                                                               String openWeatherMapApiKey) {
        return new ApiKeyRequestInterceptor(openWeatherMapApiKey);
    }

    @Bean
    public  OpenWeatherErrorDecoder openWeatherErrorDecoder(){
        return new OpenWeatherErrorDecoder();
    }
}
