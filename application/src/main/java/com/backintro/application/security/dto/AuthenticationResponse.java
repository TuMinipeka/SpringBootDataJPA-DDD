package com.backintro.application.security.dto;

public record AuthenticationResponse(
        String accessToken,
        String refreshToken,
        String tokenType,
        long expiresInSeconds
) {
    public static AuthenticationResponse bearer(
            String accessToken,
            String refreshToken,
            long expiresInSeconds
    ) {
        return new AuthenticationResponse(
                accessToken,
                refreshToken,
                "Bearer",
                expiresInSeconds
        );
    }
}
