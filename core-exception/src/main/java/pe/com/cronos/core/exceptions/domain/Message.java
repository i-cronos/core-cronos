package pe.com.cronos.core.exceptions.domain;

public enum Message {
    CORE_TOKEN_CREATION(Group.CORE_TOKEN, "C-T-1001", "Error in token creation", DefaultMessage.CORE_ERROR),
    CORE_TOKEN_VALIDATION(Group.CORE_TOKEN, "C-T-1002", "Error in token validation", DefaultMessage.CORE_ERROR),

    CORE_CRYPTO_DIGEST(Group.CORE_CRYPTO, "C-C-1003", "Error in create digest", DefaultMessage.CORE_ERROR),

    AUTH_MS_LOGIN_NOT_EXISTS(Group.CRONOS_AUTH_MS, "AUTH-MS-1001", "User mot exists", DefaultMessage.CORE_ERROR),
    AUTH_MS_LOGIN_FAILED(Group.CRONOS_AUTH_MS, "AUTH-MS-1002", "Error in login", DefaultMessage.CORE_ERROR),
    AUTH_MS_LOGIN_USER_DISABLED(Group.CRONOS_AUTH_MS, "AUTH-MS-1003", "User account disabled", DefaultMessage.CORE_ERROR),
    AUTH_MS_LOGIN_USER_BLOCKED(Group.CRONOS_AUTH_MS, "AUTH-MS-1004", "User account blocked", DefaultMessage.CORE_ERROR),
    AUTH_MS_LOGIN_USER_EXPIRED(Group.CRONOS_AUTH_MS, "AUTH-MS-1005", "User account expired", DefaultMessage.CORE_ERROR);

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
