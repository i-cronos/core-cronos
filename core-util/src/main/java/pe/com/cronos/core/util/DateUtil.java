package pe.com.cronos.core.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtil {

    public static String dateToString(LocalDate localDate, String pattern) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
        return dateFormat.format(localDate);
    }

    public static String dateTimeToString(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
        return dateFormat.format(localDateTime);
    }

    public static String zonedDateTimeToString(ZonedDateTime zonedDateTime, String pattern) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
        return dateFormat.format(zonedDateTime);
    }
}
