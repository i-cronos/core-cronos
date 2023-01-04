package pe.com.cronos.core.token.domain;

import lombok.Builder;
import lombok.Getter;
import pe.com.cronos.core.token.common.TokenType;

import java.util.Map;

@Getter
@Builder
public class TokenCreationRequest {
    private TokenType tokenType;
    private String id;
    private String[] authorities;
    private Map<String, String> data;
}
