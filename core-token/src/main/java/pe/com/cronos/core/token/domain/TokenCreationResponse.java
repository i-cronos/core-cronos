package pe.com.cronos.core.token.domain;

public record TokenCreationResponse(TokenType tokenType, String token, String summary) {

}
