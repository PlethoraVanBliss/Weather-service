package de.demo.weather.utils;

import org.junit.Test;

import java.math.BigDecimal;

import static de.demo.weather.utils.RoundingUtils.round;
import static org.junit.Assert.*;

public class RoundingUtilsTest {

    @Test
    public void testPositiveNumber(){
        assertEquals(24, round(24.174416666666673));
        assertEquals(2, round(2.5));
        assertEquals(6, round(5.56));
        assertEquals(8, round(7.61));
    }

    @Test
    public void testNegativeNumber(){
        assertEquals(new BigDecimal("-24").byteValue(), round(-24.174416666666673));
        assertEquals(new BigDecimal("-2").byteValue(), round(-2.5));
        assertEquals(new BigDecimal("-6").byteValue(), round(-5.56));
        assertEquals(new BigDecimal("-8").byteValue(), round(-7.61));
    }
}
