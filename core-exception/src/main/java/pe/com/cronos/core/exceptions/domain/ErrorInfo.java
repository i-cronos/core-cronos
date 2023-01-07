package pe.com.cronos.core.exceptions.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@ToString
public class ErrorInfo {
    private final Group group;
    private final String code;
    private final String systemMessage;
    private final String userMessage;
    private final HttpStatus httpStatus;
}
