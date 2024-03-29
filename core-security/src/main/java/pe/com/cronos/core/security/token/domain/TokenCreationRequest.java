package pe.com.cronos.core.security.token.domain;

import lombok.Builder;
import lombok.Getter;
import pe.com.cronos.core.security.token.common.TokenType;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class TokenCreationRequest {
    private TokenType tokenType;
    private String uid;
    private String credentialId;
    private List<String> authorities;
    private Map<String, String> data;
}
