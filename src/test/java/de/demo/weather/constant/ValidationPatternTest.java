package de.demo.weather.constant;

import org.junit.Test;

import java.util.regex.Pattern;

import static de.demo.weather.constant.ValidationPattern.ALPHA_ONLY;
import static org.junit.Assert.*;

public class ValidationPatternTest {

    private  static final Pattern PATTERN = Pattern.compile(ALPHA_ONLY);

    @Test
    public void testRegularName(){

        String name ="Berlin";
        assertTrue(PATTERN.matcher(name).matches());
    }

    @Test
    public void testNameContainsSpace(){

        String name ="São Paulo";
        assertTrue(PATTERN.matcher(name).matches());
    }

    @Test
    public void testNameContainsDash(){

        String name ="Saint-Denis";
        assertTrue(PATTERN.matcher(name).matches());
    }

    @Test
    public void testNameContainsNumber(){

        String name ="city 12";
        assertFalse(PATTERN.matcher(name).matches());
    }

    @Test
    public void testNameContainsComma(){

        String name ="city,one";
        assertFalse(PATTERN.matcher(name).matches());
    }
}
