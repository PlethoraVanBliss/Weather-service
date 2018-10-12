package de.demo.weather.api;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import de.demo.weather.Application;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ClockProvider;

import java.time.Clock;
import java.time.LocalDateTime;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
 import static de.demo.weather.StubUtils.FORECAST_API_URL;
import static de.demo.weather.StubUtils.getClock;
import static de.demo.weather.StubUtils.mockApi;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.HttpStatus.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, AverageForecastControllerIntegrationTest.TestConfiguration.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AverageForecastControllerIntegrationTest {

    private static final int NEVER = 0;
    private static final int ONCE = 1;
    private static final String BERLIN = "Berlin";
    private static final LocalDateTime CURRENT_DATE = LocalDateTime.of(2018, 10, 10, 23,15);

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(34477));

    @Value("http://localhost:${local.server.port}/v1/forecast/{city}")
    private String baseUrl;

    @Test
    public void testGetBerlinForecast() {

        mockApi(BERLIN, OK, "get_berlin_weather_200_response.json");

        //@formatter:off
        given()
                .get(baseUrl, BERLIN)
        .then().log().all()
                .statusCode(OK.value())
                .body("day", is(19))
                .body("night", notNullValue())
                .body("pressure", notNullValue())
        ;
        //@formatter:on
        verify(ONCE, getRequestedFor(urlPathEqualTo(FORECAST_API_URL)));
    }

    @Test
    public void testGetForecastWithInvalidApiKey() {

        String city = "PARIS";
        mockApi(city, UNAUTHORIZED, null);

        given()
                .when()
                .get(baseUrl, city)
        .then()
                .statusCode(UNAUTHORIZED.value())
        ;
        verify(ONCE, getRequestedFor(urlPathEqualTo(FORECAST_API_URL)).withQueryParam("q", equalTo(city)));
    }

    @Test
    public void testGetForecastWithInvalidCityName() {

        String city = "Berlin,Paris";
        given()
                .when()
                .get(baseUrl, city)
        .then()
                .statusCode(BAD_REQUEST.value())
        ;
        verify(NEVER, getRequestedFor(urlPathEqualTo(FORECAST_API_URL)));
    }

    @Test
    public void testGetForecastWithCityNotFound() {

        String city = "abcd";
        mockApi(city, NOT_FOUND, null);

        given()
                .when()
                .get(baseUrl, city)
        .then()
                .statusCode(NOT_FOUND.value())
        ;
        verify(ONCE, getRequestedFor(urlPathEqualTo(FORECAST_API_URL)).withQueryParam("q", equalTo(city)));
    }


    @org.springframework.boot.test.context.TestConfiguration
    static class TestConfiguration {
        @Bean
        public ClockProvider clockProvider(){
           return () -> getClock(CURRENT_DATE);
        }
    }
}
