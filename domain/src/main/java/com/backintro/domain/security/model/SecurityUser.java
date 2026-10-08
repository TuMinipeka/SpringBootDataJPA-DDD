package com.backintro.domain.security.model;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import com.backintro.domain.security.exception.SecurityDomainException;

public final class SecurityUser {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private final UUID id;
    private final String email;
    private String passwordHash;
    private final Set<Role> roles;
    private UserStatus status;

    private SecurityUser(
            UUID id,
            String email,
            String passwordHash,
            Set<Role> roles,
            UserStatus status
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.email = normalizeEmail(email);
        this.passwordHash = requireText(passwordHash, "passwordHash");
        this.roles = new LinkedHashSet<>(Objects.requireNonNull(roles, "roles must not be null"));
        this.status = Objects.requireNonNull(status, "status must not be null");
    }

    public static SecurityUser register(String email, String passwordHash) {
        return new SecurityUser(
                UUID.randomUUID(),
                email,
                passwordHash,
                Set.of(),
                UserStatus.ACTIVE
        );
    }

    public static SecurityUser restore(
            UUID id,
            String email,
            String passwordHash,
            Set<Role> roles,
            UserStatus status
    ) {
        return new SecurityUser(id, email, passwordHash, roles, status);
    }

    public void assignRole(Role role) {
        roles.add(Objects.requireNonNull(role, "role must not be null"));
    }

    public void changePasswordHash(String passwordHash) {
        this.passwordHash = requireText(passwordHash, "passwordHash");
    }

    public void block() {
        status = UserStatus.BLOCKED;
    }

    public void activate() {
        status = UserStatus.ACTIVE;
    }

    public boolean isActive() {
        return status == UserStatus.ACTIVE;
    }

    private static String normalizeEmail(String email) {
        String normalized = requireText(email, "email").toLowerCase(Locale.ROOT);
        if (!EMAIL_PATTERN.matcher(normalized).matches()) {
            throw new SecurityDomainException("email is invalid");
        }
        return normalized;
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new SecurityDomainException(field + " must not be blank");
        }
        return value.trim();
    }

    public UUID id() {
        return id;
    }

    public String email() {
        return email;
    }

    public String passwordHash() {
        return passwordHash;
    }

    public Set<Role> roles() {
        return Set.copyOf(roles);
    }

    public UserStatus status() {
        return status;
    }
}
