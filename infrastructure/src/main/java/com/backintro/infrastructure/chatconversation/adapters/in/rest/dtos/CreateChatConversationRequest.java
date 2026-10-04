package com.backintro.infrastructure.chatconversation.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateChatConversationRequest(

        @NotNull(message = "conversationStatusId is required")
        UUID conversationStatusId,

        UUID priorityId,

        LocalDateTime lastMessageAt

) {
}
