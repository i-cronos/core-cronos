package pe.com.cronos.core.token.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;


@Getter
@AllArgsConstructor
public class TokenCreationRequest {
    private Integer ttl;
    private TokenType tokenType;
    private String subject;
    private String issuer;
    private String key;
    private String id;
    private String[] authorities;
    private Map<String, String> data;
}
