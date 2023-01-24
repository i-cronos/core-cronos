package pe.com.cronos.core.security.token.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenGlobalProperties {
    private Integer ttl;
    private Integer refreshTtl;
    private String subject;
    private String issuer;

}
