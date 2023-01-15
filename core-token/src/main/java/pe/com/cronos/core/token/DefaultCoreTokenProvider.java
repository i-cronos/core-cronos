package pe.com.cronos.core.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import pe.com.cronos.core.crypto.hash.CoreDigest;
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

@AllArgsConstructor
public class DefaultCoreTokenProvider implements CoreTokenProvider {

    private final TokenGlobalProperties tokenGlobalProperties;
    private final TokenSecretProperties tokenSecretProperties;
    private final CoreDigest coreDigest;

    @Override
    public TokenCreationResponse create(TokenCreationRequest tokenCreationRequest) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(tokenSecretProperties.solve(TokenSecretProperties.KEY));
            Instant now = Instant.now();
            String token = JWT.create()
                    .withIssuer(tokenGlobalProperties.getIssuer())
                    .withSubject(tokenGlobalProperties.getSubject())
                    .withClaim(TokenConstant.TOKEN_LABEL_TYPE, tokenCreationRequest.getTokenType().name())
                    .withClaim(TokenConstant.TOKEN_LABEL_UID, tokenCreationRequest.getUid())
                    .withClaim(TokenConstant.TOKEN_LABEL_UNAME, tokenCreationRequest.getCredentialId())
                    .withArrayClaim(TokenConstant.TOKEN_LABEL_AUTHORITIES, listToArray(tokenCreationRequest.getAuthorities()))
                    .withClaim(TokenConstant.TOKEN_LABEL_DATA, tokenCreationRequest.getData())
                    .withIssuedAt(now)
                    .withExpiresAt(now.plusSeconds(tokenCreationRequest.getTokenType().getTtl()))
                    .sign(algorithm);

            String summary = coreDigest.digest(token, CoreDigest.SHA256);

            return TokenCreationResponse.builder()
                    .tokenType(tokenCreationRequest.getTokenType())
                    .token(token)
                    .summary(summary)
                    .build();

        } catch (Exception ex) {
            throw new CronosException(InfoFactory.map(Message.CORE_TOKEN_CREATION, HttpStatus.INTERNAL_SERVER_ERROR, ex));
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
                    .id(jwt.getClaim(TokenConstant.TOKEN_LABEL_UID).asString())
                    .credentialId(jwt.getClaim(TokenConstant.TOKEN_LABEL_UNAME).asString())
                    .authorities(arrayToList(jwt.getClaim(TokenConstant.TOKEN_LABEL_AUTHORITIES).asArray(String.class)))
                    .data(jwt.getClaim(TokenConstant.TOKEN_LABEL_DATA).asMap())
                    .build();


        } catch (Exception ex) {
            throw new CronosException(InfoFactory.map(Message.CORE_TOKEN_VALIDATION, HttpStatus.FORBIDDEN, ex));
        }
    }

    private String[] listToArray(List<String> authorities) {
        if (Objects.nonNull(authorities))
            return authorities.stream().toArray(String[]::new);
        return new String[]{};
    }

    private List<String> arrayToList(String[] authorities) {
        if (Objects.nonNull(authorities)) {
            return Arrays.asList(authorities);
        }
        return Collections.emptyList();
    }
}