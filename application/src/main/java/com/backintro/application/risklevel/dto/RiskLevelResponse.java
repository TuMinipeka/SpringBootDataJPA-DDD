package com.backintro.application.risklevel.dto;

import java.util.UUID;

public record RiskLevelResponse(
        UUID id,
        String name,
        String code,
        int severity
) {
}
