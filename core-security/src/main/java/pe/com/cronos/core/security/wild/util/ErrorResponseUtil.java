package pe.com.cronos.core.security.wild.util;

import org.springframework.http.HttpStatus;
import pe.com.cronos.core.exceptions.config.ErrorModel;
import pe.com.cronos.core.exceptions.domain.ErrorInfo;
import pe.com.cronos.core.exceptions.domain.Message;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public final class ErrorResponseUtil {

    public ErrorModel map(HttpServletRequest request, ErrorInfo errorInfo){
        return ErrorModel.builder()
                .timestamp(LocalDateTime.now().toString())
                .error(errorInfo.getUserMessage())
                .groupCode(errorInfo.getGroup().getCode())
                .errorCode(errorInfo.getCode())
                .status(errorInfo.getHttpStatus().name())
                .path(request.getServletPath())
                .build();
    }
    public ErrorModel map(HttpServletRequest request, Message message, HttpStatus httpStatus){
        return ErrorModel.builder()
                .timestamp(LocalDateTime.now().toString())
                .error(message.getUserMessage())
                .groupCode(message.getGroup().getCode())
                .errorCode(message.getCode())
                .status(httpStatus.name())
                .path(request.getServletPath())
                .build();
    }
}
