package com.backintro.application.clinicalrecordstatus.dto;

import java.util.UUID;

public record ClinicalRecordStatusResponse(
        UUID id,
        String name,
        String code
) {
}
