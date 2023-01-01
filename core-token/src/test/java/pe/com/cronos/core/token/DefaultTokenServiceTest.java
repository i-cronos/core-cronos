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
        TokenCreationRequest tokenRequest = new TokenCreationRequest(100, TokenType.USER, "AUTH", "CRONOS", "test pass", "testUser", new String[]{"USER"}, data);

        TokenCreationResponse tokenResponse = tokenService.create(tokenRequest);

        Assertions.assertNotNull(tokenResponse);
    }

    @Test
    void givenRequestCreationToken_whenCreate_thenThrowsException() {
        Map<String, String> data = new HashMap<>();
        data.put("data1", "Test 1");
        data.put("data2", "Test 2");
        data.put("data3", "Test 3");
        TokenCreationRequest tokenRequest = new TokenCreationRequest(null, TokenType.USER, "AUTH", "CRONOS", "test pass", "testUser", new String[]{"USER"}, data);

        Assertions.assertThrows(CronosException.class, () -> tokenService.create(tokenRequest));
    }


    @Test
    void givenTokenValidationRequest_whenValidate_thenValidateResponse() {
        String key = "Test 1";
        String issuer = "CRONOS";

        Map<String, String> data = new HashMap<>();
        data.put("data1", "Test 1");
        data.put("data2", "Test 2");
        data.put("data3", "Test 3");
        TokenCreationRequest tokenRequest = new TokenCreationRequest(100, TokenType.USER, "AUTH", issuer, key, "testUser", new String[]{"USER"}, data);

        TokenCreationResponse tokenResponse = tokenService.create(tokenRequest);

        TokenValidationRequest tokenValidationRequest = new TokenValidationRequest(issuer, key, tokenResponse.getToken());

        TokenValidationResponse tokenValidationResponse = tokenService.validate(tokenValidationRequest);

        Assertions.assertNotNull(tokenValidationResponse);
    }

    @Test
    void givenTokenValidationRequest_whenValidate_thenThrowsException() {
        String key = "Test 1";
        String issuer = "CRONOS";

        Map<String, String> data = new HashMap<>();
        data.put("data1", "Test 1");
        data.put("data2", "Test 2");
        data.put("data3", "Test 3");
        TokenCreationRequest tokenRequest = new TokenCreationRequest(-10, TokenType.USER, "AUTH", issuer, key, "testUser", new String[]{"USER"}, data);

        TokenCreationResponse tokenResponse = tokenService.create(tokenRequest);

        TokenValidationRequest tokenValidationRequest = new TokenValidationRequest(issuer, key, tokenResponse.getToken());

        Assertions.assertThrows(CronosException.class, () -> tokenService.validate(tokenValidationRequest));

    }
}