package com.backintro.application.chatconversationaisetting.dto;

import java.util.UUID;

public record ChatConversationAiSettingResponse(
        UUID id,
        UUID conversationId,
        boolean aiEnabled,
        UUID defaultModelId
) {
}
