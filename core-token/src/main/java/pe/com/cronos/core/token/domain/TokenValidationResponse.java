package pe.com.cronos.core.token.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class TokenValidationResponse {
    private TokenType tokenType;
    private String id;
    private String[] authorities;
    private Map<String, Object> data;
}
