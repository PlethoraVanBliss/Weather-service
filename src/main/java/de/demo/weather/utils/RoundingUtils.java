package de.demo.weather.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

@UtilityClass
public class RoundingUtils {

    public static short round(double number){
        return new BigDecimal(number).setScale(0, ROUND_HALF_DOWN).shortValue();
    }
}
