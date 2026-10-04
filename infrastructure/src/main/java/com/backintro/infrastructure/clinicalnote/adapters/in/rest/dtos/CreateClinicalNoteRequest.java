package com.backintro.infrastructure.clinicalnote.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateClinicalNoteRequest(

        @NotNull(message = "encounterId is required")
        UUID encounterId,

        @NotNull(message = "professionalId is required")
        UUID professionalId,

        String subjective,

        String objective,

        String assessment,

        String plan,

        String additionalNotes

) {
}
