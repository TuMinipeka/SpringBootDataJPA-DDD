package com.backintro.infrastructure.treatmentplan.adapters.in.rest.dtos;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTreatmentPlanRequest(

        @NotBlank(message = "title is required")
        @Size(max = 200, message = "title must have at most 200 characters")
        String title,

        String description,

        LocalDate endDate,

        @NotNull(message = "statusId is required")
        UUID statusId

) {
}
