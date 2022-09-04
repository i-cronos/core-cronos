package pe.com.cronos.core.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DigestTest {

    @Test
    void givenString_whenDigest_thenValidateResponse() {
        String data = "Cronos digest data test !";

        String result = Digest.digest(data);

        Assertions.assertEquals(64, result.length());
    }
}