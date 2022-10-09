package pe.com.cronos.core.exceptions;

import pe.com.cronos.core.exceptions.domain.ErrorInfo;

public class CronosException extends RuntimeException {
    private final ErrorInfo errorInfo;

    public CronosException(ErrorInfo errorInfo) {
        super(errorInfo.getSystemMessage());
        this.errorInfo = errorInfo;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }
}
