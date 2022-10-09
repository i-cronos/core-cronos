package pe.com.cronos.core.exceptions.domain;

public class ErrorInfo {
    private final Group group;
    private final String code;
    private final String systemMessage;
    private final String userMessage;

    public ErrorInfo(Group group, String code, String systemMessage, String userMessage) {
        this.group = group;
        this.code = code;
        this.systemMessage = systemMessage;
        this.userMessage = userMessage;
    }

    public Group getGroup() {
        return group;
    }

    public String getCode() {
        return code;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }
}
