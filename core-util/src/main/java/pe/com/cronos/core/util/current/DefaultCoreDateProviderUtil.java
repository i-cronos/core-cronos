package pe.com.cronos.core.util.current;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DefaultCoreDateProviderUtil implements CoreDateProviderUtil {

    @Override
    public LocalTime currentLocalTime() {
        return LocalTime.now();
    }

    @Override
    public LocalDate currentLocalDate() {
        return LocalDate.now();
    }

    @Override
    public LocalDateTime currentLocalDateTime() {
        return LocalDateTime.now();
    }
}
