package pe.com.cronos.core.token.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenType {
    USER(1200),
    APP_CLIENT(600),
    APP_PRIVATE(4200),
    APP_THIRD(600);

    private int ttl;
}
