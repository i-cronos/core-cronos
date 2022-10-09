package pe.com.cronos.core.token.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenValidationRequest {
    private String issuer;
    private String password;
    private String token;
}
