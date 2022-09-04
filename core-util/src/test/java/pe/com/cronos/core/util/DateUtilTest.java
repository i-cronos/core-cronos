package pe.com.cronos.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class DateUtilTest {

    @Test
    void givenDateAndPattern_whenDateToString_thenValidateResult() {
        LocalDate request = LocalDate.now();

        String response = DateUtil.dateToString(request, DatePattern.DD_MM_YYYY);
        System.out.println(response);
        Assertions.assertNotNull(response);
    }
}