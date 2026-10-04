package com.backintro.application.conversationstatus.dto;

import java.util.UUID;

public record ConversationStatusResponse(
        UUID id,
        String nameStatus
) {
}
