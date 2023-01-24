package pe.com.cronos.core.security.wild.service;

import org.springframework.security.core.context.SecurityContextHolder;
import pe.com.cronos.core.security.wild.domain.CoreAuthenticatedUser;

import java.util.UUID;

public class DefaultCoreContextService implements CoreContextService {

    @Override
    public CoreAuthenticatedUser get() {
        return (CoreAuthenticatedUser) SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public UUID getId() {
        CoreAuthenticatedUser user = get();
        return user.getId();
    }
}
