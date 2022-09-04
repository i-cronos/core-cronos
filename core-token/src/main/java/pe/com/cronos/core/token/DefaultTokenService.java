package pe.com.cronos.core.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import pe.com.cronos.core.crypto.Digest;
import pe.com.cronos.core.exceptions.CronosException;
import pe.com.cronos.core.exceptions.domain.InfoFactory;
import pe.com.cronos.core.exceptions.domain.Message;
import pe.com.cronos.core.token.domain.*;

import java.time.Instant;

public class DefaultTokenService implements TokenService {

    private static final String TOKEN_TYPE = "_type";
    private static final String TOKEN_ROLE = "_role";
    private static final String TOKEN_DATA = "_data";

    @Override
    public TokenCreationResponse create(TokenCreationRequest tokenCreationRequest) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(tokenCreationRequest.password());
            Instant now = Instant.now();
            String token = JWT.create()
                    .withIssuer(tokenCreationRequest.issuer())
                    .withSubject(tokenCreationRequest.subject())
                    .withClaim(TOKEN_TYPE, tokenCreationRequest.tokenType().name())
                    .withClaim(TOKEN_ROLE, tokenCreationRequest.role())
                    .withClaim(TOKEN_DATA, tokenCreationRequest.data())
                    .withIssuedAt(now)
                    .withExpiresAt(now.plusSeconds(tokenCreationRequest.ttl()))
                    .sign(algorithm);

            String summary = Digest.digest(token);

            return new TokenCreationResponse(tokenCreationRequest.tokenType(), token, summary);

        } catch (Exception ex) {
            throw new CronosException(InfoFactory.get(Message.CORE_TOKEN_CREATION, ex));
        }
    }

    @Override
    public TokenValidationResponse validate(TokenValidationRequest tokenValidationRequest) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(tokenValidationRequest.password());

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(tokenValidationRequest.issuer())
                    .build();

            DecodedJWT jwt = verifier.verify(tokenValidationRequest.token());

            return new TokenValidationResponse(
                    TokenType.valueOf(jwt.getClaim(TOKEN_TYPE).asString()),
                    jwt.getClaim(TOKEN_ROLE).asString(),
                    jwt.getClaim(TOKEN_DATA).asMap());

        } catch (Exception ex) {
            throw new CronosException(InfoFactory.get(Message.CORE_TOKEN_VALIDATION, ex));
        }
    }
}
