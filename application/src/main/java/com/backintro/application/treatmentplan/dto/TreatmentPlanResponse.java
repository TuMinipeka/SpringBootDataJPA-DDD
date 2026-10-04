package com.backintro.application.treatmentplan.dto;

import java.time.LocalDate;
import java.util.UUID;

public record TreatmentPlanResponse(
        UUID id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate
) {
}
