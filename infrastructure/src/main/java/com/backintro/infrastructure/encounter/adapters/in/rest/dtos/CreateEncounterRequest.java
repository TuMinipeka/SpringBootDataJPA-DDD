package com.backintro.infrastructure.encounter.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateEncounterRequest(

        @NotNull(message = "clinicalRecordId is required")
        UUID clinicalRecordId,

        @NotNull(message = "professionalId is required")
        UUID professionalId,

        @NotNull(message = "encounterTypeId is required")
        UUID encounterTypeId,

        @NotNull(message = "startedAt is required")
        LocalDateTime startedAt,

        String reasonForVisit,

        String currentCondition,

        @NotNull(message = "modalityId is required")
        UUID modalityId,

        @NotNull(message = "statusId is required")
        UUID statusId

) {
}
