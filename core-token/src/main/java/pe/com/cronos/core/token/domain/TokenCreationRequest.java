package pe.com.cronos.core.token.domain;

import java.util.Map;

public record TokenCreationRequest(Integer ttl,
                                   TokenType tokenType,
                                   String subject,
                                   String issuer,
                                   String password,
                                   String role,
                                   Map<String, String> data) {
}
