package com.backintro.application.clinicalrecord.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClinicalRecordResponse(
        UUID id,
        LocalDateTime creationDate,
        String recordNumber,
        LocalDateTime openedAt,
        LocalDateTime closedAt
) {
}
