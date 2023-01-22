package pe.com.cronos.core.security.service;

import pe.com.cronos.core.security.domain.CoreAuthenticatedUser;

import java.util.UUID;

public interface CoreContextService {

    CoreAuthenticatedUser get();

    UUID getId();
}
