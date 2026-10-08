package com.backintro.infrastructure.security.adapters.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AssignRoleRequest(
        @NotBlank(message = "roleName is required")
        @Size(max = 100, message = "roleName must contain at most 100 characters")
        String roleName
) {
}
