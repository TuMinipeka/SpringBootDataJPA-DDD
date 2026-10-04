package com.backintro.application.chatconversation.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChatConversationResponse(
        UUID id,
        UUID conversationStatusId,
        UUID priorityId,
        LocalDateTime lastMessageAt,
        boolean closed,
        LocalDateTime closedAt,
        UUID closedBy
) {
}
