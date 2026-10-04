package com.backintro.infrastructure.chatescalationstatushistory.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateChatEscalationStatusHistoryRequest(

        @NotNull(message = "escalationId is required")
        UUID escalationId,

        @NotNull(message = "escalationStatusId is required")
        UUID escalationStatusId

) {
}
