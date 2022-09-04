package pe.com.cronos.core.exceptions.domain;

public enum Message {
    CORE_TOKEN_CREATION(Group.CORE_TOKEN, "CCT-0001", "Error in token creation", DefaultMessage.CORE_ERROR),
    CORE_TOKEN_VALIDATION(Group.CORE_TOKEN, "CCT-0002", "Error in token validation", DefaultMessage.CORE_ERROR),
    CORE_DIGEST(Group.CORE_CRYPTO, "CCT-0001", "Error in create digest", DefaultMessage.CORE_ERROR);

    private Group group;
    private String code;
    private String systemMessage;
    private String userMessage;

    Message(Group group, String code, String systemMessage, String userMessage) {
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
