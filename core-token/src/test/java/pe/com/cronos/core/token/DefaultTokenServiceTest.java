package pe.com.cronos.core.token;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pe.com.cronos.core.exceptions.CronosException;
import pe.com.cronos.core.token.domain.*;

import java.util.HashMap;
import java.util.Map;

class DefaultTokenServiceTest {

    static TokenService tokenService;

    @BeforeAll
    static void beforeAll() {
        tokenService = new DefaultTokenService();
    }

    @Test
    void givenRequestCreationToken_whenCreate_thenValidateResponse() {
        Map<String, String> data = new HashMap<>();
        data.put("data1", "Test 1");
        data.put("data2", "Test 2");
        data.put("data3", "Test 3");

        TokenCreationRequest tokenRequest = TokenCreationRequest.builder()
                .ttl(100)
                .tokenType(TokenType.USER)
                .subject("AUTH")
                .issuer("CRONOS")
                .key("test key")
                .id("testUser")
                .authorities(new String[]{"USER"})
                .data(data)
                .build();

        TokenCreationResponse tokenResponse = tokenService.create(tokenRequest);

        Assertions.assertNotNull(tokenResponse);
    }

    @Test
    void givenRequestCreationToken_whenCreate_thenThrowsException() {
        Map<String, String> data = new HashMap<>();
        data.put("data1", "Test 1");
        data.put("data2", "Test 2");
        data.put("data3", "Test 3");

        TokenCreationRequest tokenRequest = TokenCreationRequest.builder()
                .ttl(null)
                .tokenType(TokenType.USER)
                .subject("AUTH")
                .issuer("CRONOS")
                .key("test key")
                .id("testUser")
                .authorities(new String[]{"USER"})
                .data(data)
                .build();

        Assertions.assertThrows(CronosException.class, () -> tokenService.create(tokenRequest));
    }


    @Test
    void givenTokenValidationRequest_whenValidate_thenValidateResponse() {
        String key = "Key 1";
        String issuer = "CRONOS";

        Map<String, String> data = new HashMap<>();
        data.put("data1", "Test 1");
        data.put("data2", "Test 2");
        data.put("data3", "Test 3");

        TokenCreationRequest tokenRequest = TokenCreationRequest.builder()
                .ttl(100)
                .tokenType(TokenType.USER)
                .subject("AUTH")
                .issuer("CRONOS")
                .key("Key 1")
                .id("testUser")
                .authorities(new String[]{"USER"})
                .data(data)
                .build();

        TokenCreationResponse tokenResponse = tokenService.create(tokenRequest);

        TokenValidationRequest tokenValidationRequest = TokenValidationRequest.builder()
                .issuer(issuer)
                .key(key)
                .token(tokenResponse.getToken())
                .build();

        TokenValidationResponse tokenValidationResponse = tokenService.validate(tokenValidationRequest);

        Assertions.assertNotNull(tokenValidationResponse);
    }

    @Test
    void givenTokenValidationRequest_whenValidate_thenThrowsException() {
        String key = "Key 1";
        String issuer = "CRONOS";

        Map<String, String> data = new HashMap<>();
        data.put("data1", "Test 1");
        data.put("data2", "Test 2");
        data.put("data3", "Test 3");

        TokenCreationRequest tokenRequest = TokenCreationRequest.builder()
                .ttl(-100)
                .tokenType(TokenType.USER)
                .subject("AUTH")
                .issuer("CRONOS")
                .key("Key 2")
                .id("testUser")
                .authorities(new String[]{"USER"})
                .data(data)
                .build();

        TokenCreationResponse tokenResponse = tokenService.create(tokenRequest);

        TokenValidationRequest tokenValidationRequest = TokenValidationRequest.builder()
                .issuer(issuer)
                .key(key)
                .token(tokenResponse.getToken())
                .build();

        Assertions.assertThrows(CronosException.class, () -> tokenService.validate(tokenValidationRequest));

    }
}