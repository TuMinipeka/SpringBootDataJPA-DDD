package com.backintro.application.consenttype.dto;

import java.util.UUID;

public record ConsentTypeResponse(
        UUID id,
        String name,
        String code,
        String description
) {
}
