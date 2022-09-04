package pe.com.cronos.core.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateUtil {

    public static String dateToString(LocalDate localDate, String pattern) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
        return dateFormat.format(localDate);
    }
}
