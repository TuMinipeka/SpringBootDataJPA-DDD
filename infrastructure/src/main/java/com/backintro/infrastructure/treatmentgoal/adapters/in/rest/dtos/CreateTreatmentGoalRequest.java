package com.backintro.infrastructure.treatmentgoal.adapters.in.rest.dtos;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTreatmentGoalRequest(

        @NotNull(message = "treatmentPlanId is required")
        UUID treatmentPlanId,

        @NotBlank(message = "description is required")
        String description,

        LocalDate targetDate,

        String notes,

        @NotNull(message = "statusId is required")
        UUID statusId

) {
}
