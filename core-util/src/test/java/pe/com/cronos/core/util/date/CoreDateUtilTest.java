package pe.com.cronos.core.util.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;


class CoreDateUtilTest {

    @Test
    void givenDateAndPattern_whenDateToString_thenValidateResult() {
        LocalDate request = LocalDate.now();

        String response = CoreDateUtil.dateToString(request, CoreDatePattern.DD_MM_YYYY);

        Assertions.assertNotNull(response);
    }

    @Test
    void givenDateTimeAndPattern_whenDateToString_thenValidateResult() {
        LocalDateTime request = LocalDateTime.now();

        String response = CoreDateUtil.dateTimeToString(request, CoreDatePattern.DD_MM_YYYY_H_M_S);

        Assertions.assertNotNull(response);
    }

    @Test
    void givenZonedDateTimeAndPattern_whenDateToString_thenValidateResult() {
        ZonedDateTime request = ZonedDateTime.now();
        String response = CoreDateUtil.zonedDateTimeToString(request, CoreDatePattern.DD_MM_YYYY_H_M_S_Z);

        Assertions.assertNotNull(response);
    }
}