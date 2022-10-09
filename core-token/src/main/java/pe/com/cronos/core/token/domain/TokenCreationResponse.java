package pe.com.cronos.core.token.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenCreationResponse {
    private TokenType tokenType;
    private String token;
    private String summary;
}
