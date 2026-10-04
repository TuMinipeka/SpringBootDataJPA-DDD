package com.backintro.infrastructure.clinicalrecord.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateClinicalRecordRequest(

        @NotNull(message = "patientId is required")
        UUID patientId,

        @NotBlank(message = "recordNumber is required")
        @Size(max = 50, message = "recordNumber must have at most 50 characters")
        String recordNumber,

        @NotNull(message = "statusId is required")
        UUID statusId

) {
}
