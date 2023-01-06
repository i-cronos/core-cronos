package pe.com.cronos.core.crypto;

import org.apache.commons.codec.binary.Hex;
import org.springframework.http.HttpStatus;
import pe.com.cronos.core.exceptions.CronosException;
import pe.com.cronos.core.exceptions.domain.InfoFactory;
import pe.com.cronos.core.exceptions.domain.Message;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Digest {
    public static final String SHA256 = "SHA-256";
    public static final String SHA512 = "SHA-512";

    private Digest() {
    }

    public static String digest(String data, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] result = md.digest(data.getBytes());

            return Hex.encodeHexString(result);
        } catch (NoSuchAlgorithmException ex) {
            throw new CronosException(InfoFactory.map(Message.CORE_CRYPTO_DIGEST, HttpStatus.BAD_REQUEST, ex));
        }
    }
}
