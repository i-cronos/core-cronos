package pe.com.cronos.core.token.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.com.cronos.core.crypto.hash.CoreDigest;
import pe.com.cronos.core.token.DefaultCoreTokenProvider;
import pe.com.cronos.core.token.CoreTokenProvider;
import pe.com.cronos.core.token.properties.TokenGlobalProperties;
import pe.com.cronos.core.token.properties.TokenSecretProperties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class TokenProviderAutoConfiguration {

    @Bean
    public TokenSecretProperties tokenSecretProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("/opt/token-config.properties"));
        TokenSecretProperties tokenSecretProperties = new TokenSecretProperties();
        tokenSecretProperties.setProperties(properties);

        return tokenSecretProperties;
    }

    @Bean
    @ConfigurationProperties("token.global.config")
    public TokenGlobalProperties tokenGlobalProperties() {
        return new TokenGlobalProperties();
    }

    @Bean
    public CoreTokenProvider coreTokenProvider(TokenGlobalProperties tokenGlobalProperties, TokenSecretProperties tokenSecretProperties) {
        return new DefaultCoreTokenProvider(tokenGlobalProperties, tokenSecretProperties, new CoreDigest());
    }
}
