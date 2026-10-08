package com.backintro.application.security.dto;

import java.util.Set;
import java.util.UUID;

import com.backintro.domain.security.model.Role;
import com.backintro.domain.security.model.SecurityUser;

public record SecurityUserResponse(
        UUID id,
        String email,
        Set<String> authorities,
        String status
) {
    public static SecurityUserResponse from(SecurityUser user) {
        return new SecurityUserResponse(
                user.id(),
                user.email(),
                user.roles().stream().map(Role::authority).collect(java.util.stream.Collectors.toUnmodifiableSet()),
                user.status().name()
        );
    }
}
