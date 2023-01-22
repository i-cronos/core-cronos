package pe.com.cronos.core.crypto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.com.cronos.core.crypto.hash.CoreDigest;
import pe.com.cronos.core.crypto.random.CoreStringId;

@Configuration
public class CoreCryptoAutoConfiguration {

    @Bean
    public CoreDigest coreDigest() {
        return new CoreDigest();
    }

    @Bean
    public CoreStringId coreStringId() {
        return new CoreStringId();
    }
}
