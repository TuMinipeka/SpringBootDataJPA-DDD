package com.backintro.domain.security.exception;

import java.util.UUID;

public final class SecurityUserNotFoundException extends SecurityDomainException {

    public SecurityUserNotFoundException(UUID id) {
        super("Security user not found with id: " + id);
    }
}
