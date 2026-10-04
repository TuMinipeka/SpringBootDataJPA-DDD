package com.backintro.application.patientallergy.dto;

import java.util.UUID;

public record PatientAllergyResponse(
        UUID id,
        UUID patientId,
        String substance,
        String reaction,
        String severity,
        boolean active,
        UUID recordedBy
) {
}
