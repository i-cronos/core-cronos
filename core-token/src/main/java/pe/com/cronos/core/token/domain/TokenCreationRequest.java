package pe.com.cronos.core.token.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;


@Getter
@Builder
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
