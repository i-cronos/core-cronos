package pe.com.cronos.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

class DateUtilTest {

    @Test
    void givenDateAndPattern_whenDateToString_thenValidateResult() {
        LocalDate request = LocalDate.now();

        String response = DateUtil.dateToString(request, DatePattern.DD_MM_YYYY);

        Assertions.assertNotNull(response);
    }

    @Test
    void givenDateTimeAndPattern_whenDateToString_thenValidateResult() {
        LocalDateTime request = LocalDateTime.now();

        String response = DateUtil.dateTimeToString(request, DatePattern.DD_MM_YYYY_H_M_S);

        Assertions.assertNotNull(response);
    }

    @Test
    void givenZonedDateTimeAndPattern_whenDateToString_thenValidateResult() {
        ZonedDateTime request = ZonedDateTime.now();
        String response = DateUtil.zonedDateTimeToString(request, DatePattern.DD_MM_YYYY_H_M_S_Z);

        Assertions.assertNotNull(response);
    }
}