package com.backintro.application.chatescalationassignment.command;

import java.util.Objects;

import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

public record RegisterChatEscalationAssignmentCommand(
        ChatEscalationId escalationId,
        ProfessionalId professionalId
) {

    public RegisterChatEscalationAssignmentCommand {
        Objects.requireNonNull(
                escalationId,
                "escalationId must not be null"
        );
        Objects.requireNonNull(
                professionalId,
                "professionalId must not be null"
        );
    }
}
