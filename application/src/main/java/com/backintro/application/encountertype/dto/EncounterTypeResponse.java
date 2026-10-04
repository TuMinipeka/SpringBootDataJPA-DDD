package com.backintro.application.encountertype.dto;

import java.util.UUID;

public record EncounterTypeResponse(
        UUID id,
        String name,
        String code
) {
}
