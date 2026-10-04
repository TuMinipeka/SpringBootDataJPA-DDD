package com.backintro.infrastructure.chatescalationstatushistory.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record UpdateChatEscalationStatusHistoryRequest(

        @NotNull(message = "escalationStatusId is required")
        UUID escalationStatusId

) {
}
