package com.backintro.application.encounterstatus.dto;

import java.util.UUID;

public record EncounterStatusResponse(
        UUID id,
        String name,
        String code
) {
}
