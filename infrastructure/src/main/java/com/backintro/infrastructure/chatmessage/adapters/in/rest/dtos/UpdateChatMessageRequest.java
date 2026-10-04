package com.backintro.infrastructure.chatmessage.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateChatMessageRequest(

        @NotNull(message = "messageTypeId is required")
        UUID messageTypeId,

        @NotBlank(message = "content is required")
        String content,

        String metadata

) {
}
