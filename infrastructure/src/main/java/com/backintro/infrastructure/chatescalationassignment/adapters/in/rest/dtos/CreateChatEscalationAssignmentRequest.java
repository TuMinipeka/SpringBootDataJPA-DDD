package com.backintro.infrastructure.chatescalationassignment.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateChatEscalationAssignmentRequest(

        @NotNull(message = "escalationId is required")
        UUID escalationId,

        @NotNull(message = "professionalId is required")
        UUID professionalId

) {
}
