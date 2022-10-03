package pe.com.cronos.core.crypto;

import org.apache.commons.codec.binary.Hex;
import pe.com.cronos.core.exceptions.CronosException;
import pe.com.cronos.core.exceptions.domain.InfoFactory;
import pe.com.cronos.core.exceptions.domain.Message;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Digest {

    private Digest() {
    }

    public static String digest(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] result = md.digest(data.getBytes());

            return Hex.encodeHexString(result);
        } catch (NoSuchAlgorithmException ex) {
            throw new CronosException(InfoFactory.get(Message.CORE_DIGEST, ex));
        }
    }
}