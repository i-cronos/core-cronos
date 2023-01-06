package pe.com.cronos.core.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DigestTest {

    @Test
    void givenString_whenDigest256_thenValidateResponse() {
        String data = "Cronos digest data test !";

        String result = Digest.digest(data, Digest.SHA256);

        Assertions.assertEquals(64, result.length());
    }

    @Test
    void givenString_whenDigest512_thenValidateResponse() {
        String data = "Cronos digest data test !";

        String result = Digest.digest(data, Digest.SHA512);

        Assertions.assertEquals(128, result.length());
    }
}