package pe.com.cronos.core.exceptions.domain;

public enum Group {
    CORE_TOKEN("G001", "Group core token"),
    CORE_CRYPTO("G002", "Group core crypto"),
    CORE_UTIL("G003", "Group core util"),
    AUTH_MS("G004", "Auth Microservices"),
    USER_MS("G005", "User Microservices");

    private String code;
    private String description;

    Group(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
