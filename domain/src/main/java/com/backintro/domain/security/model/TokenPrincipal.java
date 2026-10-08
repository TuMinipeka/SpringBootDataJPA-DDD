package com.backintro.domain.security.model;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public record TokenPrincipal(
        UUID userId,
        String email,
        Set<String> authorities
) {
    public TokenPrincipal {
        Objects.requireNonNull(userId, "userId must not be null");
        Objects.requireNonNull(email, "email must not be null");
        authorities = Set.copyOf(Objects.requireNonNull(authorities, "authorities must not be null"));
    }
}
