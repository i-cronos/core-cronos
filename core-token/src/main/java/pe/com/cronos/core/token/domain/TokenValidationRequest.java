package pe.com.cronos.core.token.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenValidationRequest {
    private String token;
}
