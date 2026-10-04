package com.backintro.application.assessmenttype.dto;

import java.util.UUID;

public record AssessmentTypeResponse(
        UUID id,
        String name,
        String code,
        String description
) {
}
