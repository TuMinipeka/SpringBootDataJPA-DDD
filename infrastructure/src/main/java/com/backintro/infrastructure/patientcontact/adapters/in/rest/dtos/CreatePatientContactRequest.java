package com.backintro.infrastructure.patientcontact.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreatePatientContactRequest(

        @NotNull(message = "contactId is required")
        UUID contactId,

        @NotNull(message = "patientId is required")
        UUID patientId,

        boolean primaryContact,

        boolean emergencyContact,

        @NotNull(message = "relationshipTypeId is required")
        UUID relationshipTypeId

) {
}
