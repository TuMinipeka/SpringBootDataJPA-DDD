package com.backintro.application.medicationroute.dto;

import java.util.UUID;

public record MedicationRouteResponse(
        UUID id,
        String name,
        String code
) {
}
