package pe.com.cronos.core.token.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class TokenValidationResponse {
    private TokenType tokenType;
    private String role;
    private Map<String, Object> data;
}
