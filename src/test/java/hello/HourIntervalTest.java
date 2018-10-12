package hello;

import org.junit.Test;

import java.time.*;
import java.util.Date;
import java.util.TimeZone;

public class HourIntervalTest {

    @Test
    public void testA(){

          Duration sessionTimeout = Duration.ofHours(6);
          System.out.println(sessionTimeout.toMillis());
        System.out.println(sessionTimeout.getSeconds());
        System.out.println(sessionTimeout.toDays());
        System.out.println(sessionTimeout.toHours());
        System.out.println(new Date(sessionTimeout.toMillis()+ LocalDate.now().toEpochDay()));



        System.out.println(">>>>>>ddd>>>>" + LocalDate.now().atTime(6, 0) );

        System.out.println(">>>>>>>>>>" + LocalDate.now().atTime(6, 0).toInstant(ZoneOffset.UTC).toEpochMilli());

        System.out.println("rrrrr" +  new Date(LocalDate.now().atTime(6, 0).toInstant(OffsetDateTime.now().getOffset()).toEpochMilli()));

        System.out.println(">>>>>>>>>>" + LocalDate.now().atStartOfDay().toEpochSecond(ZoneOffset.UTC) + sessionTimeout.toMillis());
        System.out.println("rssssssssr" + new Date(System.currentTimeMillis()));

        System.out.println(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(System.currentTimeMillis());

//         long millis = x.toInstant().toEpochMilli();

    }
}
