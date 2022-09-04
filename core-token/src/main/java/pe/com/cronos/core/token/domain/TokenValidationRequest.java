package pe.com.cronos.core.token.domain;

public record TokenValidationRequest(String issuer, String password, String token) {
}
