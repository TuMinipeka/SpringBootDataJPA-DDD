package com.backintro.domain.chatescalationassignment.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

public record ChatEscalationAssignmentUpdatedEvent(
        ChatEscalationAssignmentId id,
        ProfessionalId professionalId,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatEscalationAssignmentUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(
                professionalId,
                "professionalId must not be null"
        );
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
