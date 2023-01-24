package pe.com.cronos.core.security.token.domain;

import lombok.Builder;
import lombok.Getter;
import pe.com.cronos.core.security.token.common.TokenType;

@Getter
@Builder
public class TokenCreationResponse {
    private TokenType tokenType;
    private String token;
    private String summary;
}
