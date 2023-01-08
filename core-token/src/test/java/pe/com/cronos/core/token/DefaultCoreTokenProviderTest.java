package pe.com.cronos.core.token;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pe.com.cronos.core.crypto.hash.CoreDigest;
import pe.com.cronos.core.token.common.TokenType;
import pe.com.cronos.core.token.domain.TokenCreationRequest;
import pe.com.cronos.core.token.domain.TokenCreationResponse;
import pe.com.cronos.core.token.domain.TokenValidationRequest;
import pe.com.cronos.core.token.domain.TokenValidationResponse;
import pe.com.cronos.core.token.properties.TokenGlobalProperties;
import pe.com.cronos.core.token.properties.TokenSecretProperties;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class DefaultCoreTokenProviderTest {


    static CoreTokenProvider tokenProvider;


    @BeforeAll
    static void beforeAll() {
        TokenGlobalProperties tokenGlobalProperties = new TokenGlobalProperties();
        tokenGlobalProperties.setTtl(500);
        tokenGlobalProperties.setSubject("AUTH");
        tokenGlobalProperties.setIssuer("CRONOS");
        tokenGlobalProperties.setRefreshTtl(5000);

        TokenSecretProperties tokenSecretProperties = new TokenSecretProperties();
        Properties properties = new Properties();
        properties.setProperty(TokenSecretProperties.KEY, "Test key");
        tokenSecretProperties.setProperties(properties);

        tokenProvider = new DefaultCoreTokenProvider(tokenGlobalProperties, tokenSecretProperties, new CoreDigest());
    }

    @Test
    void givenRequestCreationToken_whenCreate_thenValidateResponse() {
        Map<String, String> data = new HashMap<>();
        data.put("data1", "Test 1");
        data.put("data2", "Test 2");
        data.put("data3", "Test 3");

        TokenCreationRequest tokenRequest = TokenCreationRequest.builder()
                .tokenType(TokenType.PERSON)
                .uid("3be9b62181bd5269a20f454f6b5574d43f38e824c40c602ea9622a0ba96f76b7")
                .id("testUser")
                .authorities(Collections.singletonList("USER"))
                .data(data)
                .build();

        TokenCreationResponse tokenResponse = tokenProvider.create(tokenRequest);

        Assertions.assertNotNull(tokenResponse);
    }

    @Test
    void givenTokenValidationRequest_whenValidate_thenValidateResponse() {
        Map<String, String> data = new HashMap<>();
        data.put("data1", "Test 1");
        data.put("data2", "Test 2");
        data.put("data3", "Test 3");

        TokenCreationRequest tokenRequest = TokenCreationRequest.builder()
                .tokenType(TokenType.PERSON)
                .uid("3be9b62181bd5269a20f454f6b5574d43f38e824c40c602ea9622a0ba96f76b7")
                .id("testUser")
                .authorities(Collections.singletonList("USER"))
                .data(data)
                .build();

        TokenCreationResponse tokenResponse = tokenProvider.create(tokenRequest);

        TokenValidationRequest tokenValidationRequest = TokenValidationRequest.builder()
                .token(tokenResponse.getToken())
                .build();

        TokenValidationResponse tokenValidationResponse = tokenProvider.validate(tokenValidationRequest);

        Assertions.assertNotNull(tokenValidationResponse);
    }

}
