package pe.com.cronos.core.constant.authorities;

public enum SystemAuthority {
    ROLE_ADMIN("ROLE_ADMIN", "ROLE", "All operation"),
    ROLE_USER("ROLE_USER", "ROLE", "custom operation"),
    ROLE_GUEST("ROLE_GUEST", "ROLE", "limit operation");

    private String name;
    private String type;
    private String description;

    SystemAuthority(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
