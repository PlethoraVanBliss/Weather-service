package de.demo.weather.config.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import javax.annotation.Nonnull;

public class ApiKeyRequestInterceptor implements RequestInterceptor {

    private final String apiKeyValue;

    public ApiKeyRequestInterceptor(@Nonnull String apiKey) {
        this.apiKeyValue = apiKey;
    }

    public void apply(RequestTemplate template) {
        template.query("apikey", apiKeyValue);
    }
}