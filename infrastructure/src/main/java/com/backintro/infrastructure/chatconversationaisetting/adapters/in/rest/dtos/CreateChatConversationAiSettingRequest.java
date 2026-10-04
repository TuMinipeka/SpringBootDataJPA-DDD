package com.backintro.infrastructure.chatconversationaisetting.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateChatConversationAiSettingRequest(

        @NotNull(message = "conversationId is required")
        UUID conversationId,

        UUID defaultModelId

) {
}
