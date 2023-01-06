package pe.com.cronos.core.exceptions.config;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class ErrorModel {
    private LocalDateTime timestamp;
    private String status;
    private String error;
    private String groupCode;
    private String errorCode;
    private String path;
}
