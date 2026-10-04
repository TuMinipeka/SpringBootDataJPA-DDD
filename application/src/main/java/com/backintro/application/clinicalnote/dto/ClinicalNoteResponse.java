package com.backintro.application.clinicalnote.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClinicalNoteResponse(
        UUID id,
        String subjective,
        String objective,
        String assessment,
        String plan,
        String additionalNotes,
        LocalDateTime signedAt
) {
}
