package de.demo.weather;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.time.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@UtilityClass
public class StubUtils {

    private static final String VALID_API_KEY = "b6907d289e10d714a6e88b30761e22";
    public static final String FORECAST_API_URL = "/data/2.5/forecast";


    public static void mockApi(@Nonnull String city,
                         @Nonnull HttpStatus responseStatus,
                         @Nullable String responseBody) {
        stubFor(get(urlPathEqualTo(FORECAST_API_URL))
                .withQueryParam("q", equalTo(city))
                .withQueryParam("apikey", equalTo(VALID_API_KEY))
                .willReturn(aResponse()
                        .withStatus(responseStatus.value())
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBodyFile(responseBody)));
    }

    public static Clock getClock(LocalDateTime dateTime){
            return Clock.fixed(dateTime.toInstant(ZoneOffset.UTC), ZoneId.of(ZoneOffset.UTC.getId()));
    }
}
