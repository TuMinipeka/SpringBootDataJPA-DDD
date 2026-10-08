package com.backintro.domain.security.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.backintro.domain.security.exception.SecurityDomainException;

public final class RefreshToken {

    private final UUID id;
    private final UUID userId;
    private final String token;
    private final LocalDateTime expiresAt;
    private boolean revoked;

    private RefreshToken(
            UUID id,
            UUID userId,
            String token,
            LocalDateTime expiresAt,
            boolean revoked
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.userId = Objects.requireNonNull(userId, "userId must not be null");
        if (token == null || token.isBlank()) {
            throw new SecurityDomainException("token must not be blank");
        }
        this.token = token;
        this.expiresAt = Objects.requireNonNull(expiresAt, "expiresAt must not be null");
        this.revoked = revoked;
    }

    public static RefreshToken issue(UUID userId, String token, LocalDateTime expiresAt) {
        return new RefreshToken(UUID.randomUUID(), userId, token, expiresAt, false);
    }

    public static RefreshToken restore(
            UUID id,
            UUID userId,
            String token,
            LocalDateTime expiresAt,
            boolean revoked
    ) {
        return new RefreshToken(id, userId, token, expiresAt, revoked);
    }

    public void revoke() {
        revoked = true;
    }

    public boolean isValidAt(LocalDateTime now) {
        return !revoked && now.isBefore(expiresAt);
    }

    public UUID id() {
        return id;
    }

    public UUID userId() {
        return userId;
    }

    public String token() {
        return token;
    }

    public LocalDateTime expiresAt() {
        return expiresAt;
    }

    public boolean revoked() {
        return revoked;
    }
}
