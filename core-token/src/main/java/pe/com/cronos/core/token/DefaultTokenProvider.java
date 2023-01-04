package pe.com.cronos.core.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import pe.com.cronos.core.crypto.Digest;
import pe.com.cronos.core.exceptions.CronosException;
import pe.com.cronos.core.exceptions.domain.InfoFactory;
import pe.com.cronos.core.exceptions.domain.Message;
import pe.com.cronos.core.token.common.TokenConstant;
import pe.com.cronos.core.token.common.TokenType;
import pe.com.cronos.core.token.domain.*;
import pe.com.cronos.core.token.properties.TokenGlobalProperties;
import pe.com.cronos.core.token.properties.TokenSecretProperties;

import java.time.Instant;
import java.util.*;

public class DefaultTokenProvider implements TokenProvider {

    private final TokenGlobalProperties tokenGlobalProperties;
    private final TokenSecretProperties tokenSecretProperties;

    public DefaultTokenProvider(TokenGlobalProperties tokenGlobalProperties, TokenSecretProperties tokenSecretProperties) {
        this.tokenGlobalProperties = tokenGlobalProperties;
        this.tokenSecretProperties = tokenSecretProperties;
    }

    @Override
    public TokenCreationResponse create(TokenCreationRequest tokenCreationRequest) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(tokenSecretProperties.solve(TokenSecretProperties.KEY));
            Instant now = Instant.now();
            String token = JWT.create()
                    .withIssuer(tokenGlobalProperties.getIssuer())
                    .withSubject(tokenGlobalProperties.getSubject())
                    .withClaim(TokenConstant.TOKEN_LABEL_TYPE, tokenCreationRequest.getTokenType().name())
                    .withClaim(TokenConstant.TOKEN_LABEL_ID, tokenCreationRequest.getId())
                    .withArrayClaim(TokenConstant.TOKEN_LABEL_AUTHORITIES, listToArray(tokenCreationRequest.getAuthorities()))
                    .withClaim(TokenConstant.TOKEN_LABEL_DATA, tokenCreationRequest.getData())
                    .withIssuedAt(now)
                    .withExpiresAt(now.plusSeconds(tokenGlobalProperties.getTtl()))
                    .sign(algorithm);

            String summary = Digest.digest(token);

            return TokenCreationResponse.builder()
                    .tokenType(tokenCreationRequest.getTokenType())
                    .token(token)
                    .summary(summary)
                    .build();

        } catch (Exception ex) {
            throw new CronosException(InfoFactory.get(Message.CORE_TOKEN_CREATION, ex));
        }
    }

    @Override
    public TokenValidationResponse validate(TokenValidationRequest tokenValidationRequest) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(tokenSecretProperties.solve(TokenSecretProperties.KEY));

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(tokenGlobalProperties.getIssuer())
                    .build();

            DecodedJWT jwt = verifier.verify(tokenValidationRequest.getToken());

            return TokenValidationResponse.builder()
                    .tokenType(TokenType.valueOf(jwt.getClaim(TokenConstant.TOKEN_LABEL_TYPE).asString()))
                    .id(jwt.getClaim(TokenConstant.TOKEN_LABEL_ID).asString())
                    .authorities(arrayToList(jwt.getClaim(TokenConstant.TOKEN_LABEL_AUTHORITIES).asArray(String.class)))
                    .data(jwt.getClaim(TokenConstant.TOKEN_LABEL_DATA).asMap())
                    .build();


        } catch (Exception ex) {
            throw new CronosException(InfoFactory.get(Message.CORE_TOKEN_VALIDATION, ex));
        }
    }

    private String[] listToArray(List<String> authorities) {
        if (Objects.nonNull(authorities))
            return authorities.stream().toArray(String[]::new);
        return null;
    }

    private List<String> arrayToList(String[] authorities) {
        if (Objects.nonNull(authorities)) {
            return Arrays.asList(authorities);
        }
        return null;
    }
}
