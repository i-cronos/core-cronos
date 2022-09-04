package pe.com.cronos.core.exceptions.domain;

public enum Group {
    CORE_TOKEN("G001", "Group core token"),
    CORE_UTIL("G002", "Group core util");

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
