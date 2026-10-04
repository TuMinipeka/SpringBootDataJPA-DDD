package com.backintro.application.treatmentstatus.dto;

import java.util.UUID;

public record TreatmentStatusResponse(
        UUID id,
        String name,
        String code
) {
}
