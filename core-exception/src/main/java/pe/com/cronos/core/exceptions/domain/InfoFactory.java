package pe.com.cronos.core.exceptions.domain;

import org.springframework.http.HttpStatus;

public class InfoFactory {

    public static ErrorInfo map(Message message, HttpStatus httpStatus) {
        return new ErrorInfo(
                message.getGroup(),
                message.getCode(),
                message.getSystemMessage(),
                message.getUserMessage(),
                httpStatus);
    }

    public static ErrorInfo map(Message message, HttpStatus httpStatus, Exception ex) {
        return new ErrorInfo(
                message.getGroup(),
                message.getCode(),
                message.getSystemMessage() + " : " + ex.getMessage(),
                message.getUserMessage(),
                httpStatus);
    }

}
