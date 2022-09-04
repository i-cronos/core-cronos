package pe.com.cronos.core.exceptions.domain;

public class InfoFactory {

    public static ErrorInfo get(Message message) {
        return new ErrorInfo(
                message.getGroup(),
                message.getCode(),
                message.getSystemMessage(),
                message.getUserMessage());
    }

    public static ErrorInfo get(Message message, Exception ex) {
        return new ErrorInfo(
                message.getGroup(),
                message.getCode(),
                message.getSystemMessage() + " : " + ex.getMessage(),
                message.getUserMessage());
    }

}
