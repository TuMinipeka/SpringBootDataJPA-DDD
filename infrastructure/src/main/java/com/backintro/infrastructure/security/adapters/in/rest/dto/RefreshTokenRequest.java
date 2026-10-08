package com.backintro.infrastructure.security.adapters.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RefreshTokenRequest(
        @NotBlank(message = "refreshToken is required")
        @Size(max = 255, message = "refreshToken must contain at most 255 characters")
        String refreshToken
) {
}
