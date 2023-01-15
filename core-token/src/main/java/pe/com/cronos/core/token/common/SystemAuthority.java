package pe.com.cronos.core.token.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemAuthority {
    ROLE_ADMIN("ROLE_ADMIN", TokenType.USER, "All operation"),
    ROLE_OPERATOR("ROLE_OPERATOR", TokenType.USER, "All operation"),
    ROLE_USER("ROLE_USER", TokenType.USER, "custom operation"),
    ROLE_APP_CLIENT("ROLE_APP_CLIENT", TokenType.APP_CLIENT, "app operation"),
    ROLE_APP_CLIENT_PRIVATE("ROLE_APP_CLIENT_PRIVATE", TokenType.APP_PRIVATE, "app operation"),
    ROLE_APP_CLIENT_THIRD("ROLE_APP_CLIENT_THIRD", TokenType.APP_THIRD, "app operation");

    private String name;
    private TokenType tokenType;
    private String description;
}
