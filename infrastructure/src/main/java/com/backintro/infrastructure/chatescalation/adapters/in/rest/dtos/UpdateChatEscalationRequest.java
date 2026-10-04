package com.backintro.infrastructure.chatescalation.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateChatEscalationRequest(

        @NotNull(message = "statusId is required")
        UUID statusId,

        boolean fromAi,

        @NotBlank(message = "reason is required")
        String reason

) {
}
