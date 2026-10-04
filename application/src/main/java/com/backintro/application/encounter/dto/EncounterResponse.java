package com.backintro.application.encounter.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EncounterResponse(
        UUID id,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        String reasonForVisit,
        String currentCondition
) {
}
