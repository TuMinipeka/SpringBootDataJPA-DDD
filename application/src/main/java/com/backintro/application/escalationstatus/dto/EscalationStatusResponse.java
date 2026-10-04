package com.backintro.application.escalationstatus.dto;

import java.util.UUID;

public record EscalationStatusResponse(
        UUID id,
        String nameStatus
) {
}
