package com.backintro.application.chatairun.dto;

import java.util.UUID;

public record ChatAiRunResponse(
        UUID id,
        UUID conversationId,
        UUID messageId,
        UUID modelId,
        UUID aiRunStatusId
) {
}
