package pe.com.cronos.core.token;

import pe.com.cronos.core.token.domain.TokenCreationRequest;
import pe.com.cronos.core.token.domain.TokenCreationResponse;
import pe.com.cronos.core.token.domain.TokenValidationRequest;
import pe.com.cronos.core.token.domain.TokenValidationResponse;

public interface TokenService {

    TokenCreationResponse create(TokenCreationRequest tokenCreationRequest);

    TokenValidationResponse validate(TokenValidationRequest tokenValidationRequest);

}
