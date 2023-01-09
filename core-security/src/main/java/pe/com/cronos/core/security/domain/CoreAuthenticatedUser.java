package pe.com.cronos.core.security.domain;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

public class CoreAuthenticatedUser extends UsernamePasswordAuthenticationToken {

    private final UUID id;

    public CoreAuthenticatedUser(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, UUID id) {
        super(principal, credentials, authorities);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
