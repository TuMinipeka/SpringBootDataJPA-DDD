package com.backintro.application.chatmessage.dto;

import java.util.UUID;

public record ChatMessageResponse(
        UUID id,
        UUID conversationId,
        UUID messageTypeId,
        UUID participantId,
        String content,
        String metadata
) {
}
