package com.backintro.infrastructure.security.exception;

import java.time.Instant;

public record SecurityErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path
) {
}
