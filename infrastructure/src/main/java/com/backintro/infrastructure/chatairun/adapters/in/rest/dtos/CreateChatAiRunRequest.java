package com.backintro.infrastructure.chatairun.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateChatAiRunRequest(

        @NotNull(message = "conversationId is required")
        UUID conversationId,

        UUID messageId,

        @NotNull(message = "modelId is required")
        UUID modelId,

        @NotNull(message = "aiRunStatusId is required")
        UUID aiRunStatusId

) {
}
