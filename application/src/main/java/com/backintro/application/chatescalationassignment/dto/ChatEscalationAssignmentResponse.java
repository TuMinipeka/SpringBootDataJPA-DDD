package com.backintro.application.chatescalationassignment.dto;

import java.util.UUID;

public record ChatEscalationAssignmentResponse(
        UUID id,
        UUID escalationId,
        UUID professionalId
) {
}
