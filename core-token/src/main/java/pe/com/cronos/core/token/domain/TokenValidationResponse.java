package pe.com.cronos.core.token.domain;

import java.util.Map;

public record TokenValidationResponse(TokenType tokenType, String role, Map<String, Object> data) {
}
