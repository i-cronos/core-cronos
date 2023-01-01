package pe.com.cronos.core.token.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class TokenValidationResponse {
    private TokenType tokenType;
    private String id;
    private String[] authorities;
    private Map<String, Object> data;
}
