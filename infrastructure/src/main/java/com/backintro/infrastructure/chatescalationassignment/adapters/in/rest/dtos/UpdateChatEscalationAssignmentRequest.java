package com.backintro.infrastructure.chatescalationassignment.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record UpdateChatEscalationAssignmentRequest(

        @NotNull(message = "professionalId is required")
        UUID professionalId

) {
}
