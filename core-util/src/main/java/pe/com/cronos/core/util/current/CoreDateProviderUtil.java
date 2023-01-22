package pe.com.cronos.core.util.current;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface CoreDateProviderUtil {

    LocalTime currentLocalTime();

    LocalDate currentLocalDate();

    LocalDateTime currentLocalDateTime();

}
