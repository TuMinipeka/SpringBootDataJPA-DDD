package com.backintro.application.encountermodality.dto;

import java.util.UUID;

public record EncounterModalityResponse(
        UUID id,
        String name,
        String code
) {
}
