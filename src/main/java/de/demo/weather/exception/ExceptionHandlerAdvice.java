package de.demo.weather.exception;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static final String MSG  = "message";
    private static final String STATUS  = "status";

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Map handleNotFound(NotFoundException e) {
        log.warn(
                "No Weather details was found while processing request. Error message [{}]",
                e.getMessage());
        return errorMessage(BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<Map> handleValidationErrors(ConstraintViolationException e) {
        log.warn("Input validation exception while processing request. Error message [{}]", e.getMessage());
        return e.getConstraintViolations().stream()
                .map(er->errorMessage(BAD_REQUEST.value(), er.getMessage()))
                .collect(toList());
    }

    @ResponseBody
    @ExceptionHandler(GeneralFeignException.class)
    public Map handleFeignErrors(HttpServletResponse response, GeneralFeignException e) throws Exception {
        log.error("General feign error  with [{}]", e.getResponse().status(), e);
        return errorMessage(e.getResponse().status(), e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(OpenWeatherApiNotAuthorizedException.class)
    public Map handleNotAuthorizedException(Exception e) {
        log.debug("Not authorized to access 0pen weather api", e);
        return errorMessage(UNAUTHORIZED.value(), e.getMessage());
    }

    private static Map errorMessage(int httpStatus, String message){
        return ImmutableMap.of(STATUS, httpStatus, MSG, message);
    }
}
