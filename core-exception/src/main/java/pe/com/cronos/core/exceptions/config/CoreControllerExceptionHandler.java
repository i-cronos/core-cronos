package pe.com.cronos.core.exceptions.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pe.com.cronos.core.exceptions.CronosException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


@Slf4j
@ControllerAdvice
public class CoreControllerExceptionHandler {

    @ExceptionHandler(value = {CronosException.class})
    public ResponseEntity<ErrorModel> cronosException(CronosException ex, HttpServletRequest request) {
        ErrorModel message = ErrorModel.builder()
                .timestamp(LocalDateTime.now())
                .error(ex.getErrorInfo().getUserMessage())
                .groupCode(ex.getErrorInfo().getGroup().getCode())
                .errorCode(ex.getErrorInfo().getCode())
                .status(ex.getErrorInfo().getHttpStatus().name())
                .path(request.getServletPath())
                .build();

        log.error("Detail: {}", message);
        return new ResponseEntity<>(message, ex.getErrorInfo().getHttpStatus());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorModel> exception(Exception ex, HttpServletRequest request) {
        ErrorModel message = ErrorModel.builder()
                .timestamp(LocalDateTime.now())
                .error(ex.getMessage())
                .groupCode("")
                .errorCode("")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .path(request.getServletPath())
                .build();

        log.error("Detail: {}", message);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
