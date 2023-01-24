package pe.com.cronos.core.security.token.properties;

import lombok.Getter;
import lombok.Setter;

import java.util.Properties;

@Getter
@Setter
public class TokenSecretProperties {
    public static final String KEY = "token.key";

    private Properties properties;

    public String solve(String label) {
        return properties.getProperty(label);
    }
}
