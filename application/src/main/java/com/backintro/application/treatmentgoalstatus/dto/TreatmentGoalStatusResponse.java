package com.backintro.application.treatmentgoalstatus.dto;

import java.util.UUID;

public record TreatmentGoalStatusResponse(
        UUID id,
        String name,
        String code
) {
}
