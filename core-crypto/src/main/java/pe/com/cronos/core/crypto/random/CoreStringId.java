package pe.com.cronos.core.crypto.random;

import pe.com.cronos.core.crypto.hash.CoreDigest;

import java.time.LocalDateTime;
import java.util.UUID;

public final class CoreStringId {
    private final CoreDigest coreDigest;

    public CoreStringId() {
        this.coreDigest = new CoreDigest();
    }

    private String randomId() {
        String id = UUID.randomUUID().toString() + LocalDateTime.now();

        return coreDigest.digest(id, CoreDigest.SHA256);
    }
}
