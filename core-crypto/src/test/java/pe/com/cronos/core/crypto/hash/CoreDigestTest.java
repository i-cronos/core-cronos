package pe.com.cronos.core.crypto.hash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoreDigestTest {

    private CoreDigest coreDigest;

    @BeforeEach
    void setUp() {
        coreDigest = new CoreDigest();
    }

    @Test
    void givenString_whenDigest256_thenValidateResponse() {
        String data = "Cronos digest data test !";

        String result = coreDigest.digest(data, CoreDigest.SHA256);

        Assertions.assertEquals(64, result.length());
    }

    @Test
    void givenString_whenDigest512_thenValidateResponse() {
        String data = "Cronos digest data test !";

        String result = coreDigest.digest(data, CoreDigest.SHA512);

        Assertions.assertEquals(128, result.length());
    }
}