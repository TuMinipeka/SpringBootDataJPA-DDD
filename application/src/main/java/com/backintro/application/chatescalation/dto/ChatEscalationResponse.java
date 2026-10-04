package com.backintro.application.chatescalation.dto;

import java.util.UUID;

public record ChatEscalationResponse(
        UUID id,
        UUID conversationId,
        UUID statusId,
        boolean fromAi,
        String reason
) {
}
