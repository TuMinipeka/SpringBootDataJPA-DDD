package com.backintro.application.professionalstudy.dto;

import java.util.UUID;

public record ProfessionalStudyResponse(
        UUID id,
        String title,
        String university,
        boolean valid,
        String resolutionNumber
) {
}
