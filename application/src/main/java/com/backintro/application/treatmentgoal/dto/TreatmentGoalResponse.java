package com.backintro.application.treatmentgoal.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TreatmentGoalResponse(
        UUID id,
        String description,
        LocalDate targetDate,
        LocalDateTime completedAt,
        String notes
) {
}
