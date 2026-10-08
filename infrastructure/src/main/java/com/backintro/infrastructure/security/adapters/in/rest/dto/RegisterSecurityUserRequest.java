package com.backintro.infrastructure.security.adapters.in.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterSecurityUserRequest(
        @NotBlank(message = "email is required")
        @Email(message = "email must be valid")
        @Size(max = 320, message = "email must contain at most 320 characters")
        String email,

        @NotBlank(message = "password is required")
        @Size(min = 8, max = 72, message = "password must contain between 8 and 72 characters")
        String password
) {
}
