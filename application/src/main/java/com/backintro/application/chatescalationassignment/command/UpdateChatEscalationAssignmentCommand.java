package com.backintro.application.chatescalationassignment.command;

import java.util.Objects;

import com.backintro.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

public record UpdateChatEscalationAssignmentCommand(
        ChatEscalationAssignmentId id,
        ProfessionalId professionalId
) {

    public UpdateChatEscalationAssignmentCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(
                professionalId,
                "professionalId must not be null"
        );
    }
}
