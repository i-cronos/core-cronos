package pe.com.cronos.core.token.domain;

import lombok.Builder;
import lombok.Getter;
import pe.com.cronos.core.token.common.TokenType;

@Getter
@Builder
public class TokenCreationResponse {
    private TokenType tokenType;
    private String token;
    private String summary;
}
