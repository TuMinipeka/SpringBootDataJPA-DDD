package com.backintro.infrastructure.treatmentgoal.adapters.in.rest.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTreatmentGoalRequest(

        @NotBlank(message = "description is required")
        String description,

        LocalDate targetDate,

        LocalDateTime completedAt,

        String notes,

        @NotNull(message = "statusId is required")
        UUID statusId

) {
}
