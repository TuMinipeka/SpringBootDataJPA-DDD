package com.backintro.application.airunstatus.dto;

import java.util.UUID;

public record AiRunStatusResponse(
        UUID id,
        String nameStatus
) {
}
