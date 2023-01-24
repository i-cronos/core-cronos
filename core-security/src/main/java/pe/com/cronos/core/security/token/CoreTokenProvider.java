package pe.com.cronos.core.security.token;

import pe.com.cronos.core.security.token.domain.TokenCreationRequest;
import pe.com.cronos.core.security.token.domain.TokenCreationResponse;
import pe.com.cronos.core.security.token.domain.TokenValidationRequest;
import pe.com.cronos.core.security.token.domain.TokenValidationResponse;

public interface CoreTokenProvider {

    TokenCreationResponse create(TokenCreationRequest tokenCreationRequest);

    TokenValidationResponse validate(TokenValidationRequest tokenValidationRequest);

}
