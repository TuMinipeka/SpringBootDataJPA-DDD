package com.backintro.application.professionaltype.dto;

import java.util.UUID;

public record ProfessionalTypeResponse(
        UUID id,
        String name
) {
}
