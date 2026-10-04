package com.backintro.application.professional.dto;

import java.util.UUID;

public record ProfessionalResponse(
        UUID id,
        String documentNumber,
        String firstName,
        String lastName,
        String licenseNumber
) {
}
