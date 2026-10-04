package com.backintro.infrastructure.chatairunerror.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateChatAiRunErrorRequest(

        @NotNull(message = "aiRunId is required")
        UUID aiRunId,

        @NotBlank(message = "errorMessage is required")
        String errorMessage,

        @Size(max = 30, message = "errorCode must have at most 30 characters")
        String errorCode,

        @Size(
                max = 120,
                message = "providerErrorId must have at most 120 characters"
        )
        String providerErrorId

) {
}
