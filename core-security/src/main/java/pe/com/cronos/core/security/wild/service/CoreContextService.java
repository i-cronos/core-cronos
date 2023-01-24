package pe.com.cronos.core.security.wild.service;

import pe.com.cronos.core.security.wild.domain.CoreAuthenticatedUser;

import java.util.UUID;

public interface CoreContextService {

    CoreAuthenticatedUser get();

    UUID getId();
}
