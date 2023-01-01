package pe.com.cronos.core.token.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenValidationRequest {
    private String issuer;
    private String key;
    private String token;
}
