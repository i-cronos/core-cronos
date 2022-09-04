package pe.com.cronos.core.exceptions;

import pe.com.cronos.core.exceptions.domain.ErrorInfo;

public class CronosException extends RuntimeException {
    private ErrorInfo errorInfo;

    public CronosException(ErrorInfo errorInfo) {
        super(errorInfo.systemMessage());
        this.errorInfo = errorInfo;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }
}
