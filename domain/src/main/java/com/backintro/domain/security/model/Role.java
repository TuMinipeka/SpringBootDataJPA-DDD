package com.backintro.domain.security.model;

import java.util.Objects;
import java.util.UUID;

import com.backintro.domain.security.exception.SecurityDomainException;

public final class Role {

    private final UUID id;
    private final String name;
    private final String authority;

    private Role(UUID id, String name, String authority) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = normalize(name, "name");
        this.authority = normalize(authority, "authority");
    }

    public static Role create(String name, String authority) {
        return new Role(UUID.randomUUID(), name, authority);
    }

    public static Role restore(UUID id, String name, String authority) {
        return new Role(id, name, authority);
    }

    private static String normalize(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new SecurityDomainException(field + " must not be blank");
        }
        return value.trim().toUpperCase();
    }

    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String authority() {
        return authority;
    }

    @Override
    public boolean equals(Object candidate) {
        return this == candidate
                || candidate instanceof Role role && id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
