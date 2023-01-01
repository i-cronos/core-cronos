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

    private static final String TOKEN_LABEL_ID = "_id";
    private static final String TOKEN_LABEL_TYPE = "_type";
    private static final String TOKEN_LABEL_AUTHORITIES = "_grants";
    private static final String TOKEN_LABEL_DATA = "_data";

    @Override
    public TokenCreationResponse create(TokenCreationRequest tokenCreationRequest) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(tokenCreationRequest.getKey());
            Instant now = Instant.now();
            String token = JWT.create()
                    .withIssuer(tokenCreationRequest.getIssuer())
                    .withSubject(tokenCreationRequest.getSubject())
                    .withClaim(TOKEN_LABEL_TYPE, tokenCreationRequest.getTokenType().name())
                    .withClaim(TOKEN_LABEL_ID, tokenCreationRequest.getId())
                    .withArrayClaim(TOKEN_LABEL_AUTHORITIES, tokenCreationRequest.getAuthorities())
                    .withClaim(TOKEN_LABEL_DATA, tokenCreationRequest.getData())
                    .withIssuedAt(now)
                    .withExpiresAt(now.plusSeconds(tokenCreationRequest.getTtl()))
                    .sign(algorithm);

            String summary = Digest.digest(token);

            return new TokenCreationResponse(tokenCreationRequest.getTokenType(), token, summary);

        } catch (Exception ex) {
            throw new CronosException(InfoFactory.get(Message.CORE_TOKEN_CREATION, ex));
        }
    }

    @Override
    public TokenValidationResponse validate(TokenValidationRequest tokenValidationRequest) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(tokenValidationRequest.getPassword());

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(tokenValidationRequest.getIssuer())
                    .build();

            DecodedJWT jwt = verifier.verify(tokenValidationRequest.getToken());

            return new TokenValidationResponse(
                    TokenType.valueOf(jwt.getClaim(TOKEN_LABEL_TYPE).asString()),
                    jwt.getClaim(TOKEN_LABEL_ID).asString(),
                    jwt.getClaim(TOKEN_LABEL_AUTHORITIES).asArray(String.class),
                    jwt.getClaim(TOKEN_LABEL_DATA).asMap());

        } catch (Exception ex) {
            throw new CronosException(InfoFactory.get(Message.CORE_TOKEN_VALIDATION, ex));
        }
    }
}
