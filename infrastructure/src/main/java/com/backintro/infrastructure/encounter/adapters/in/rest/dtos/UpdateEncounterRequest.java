package com.backintro.infrastructure.encounter.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record UpdateEncounterRequest(

        LocalDateTime endedAt,

        String reasonForVisit,

        String currentCondition,

        @NotNull(message = "statusId is required")
        UUID statusId

) {
}
