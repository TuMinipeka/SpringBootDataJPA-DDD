package com.backintro.domain.chatescalationassignment.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.backintro.domain.common.event.DomainEvent;

public record ChatEscalationAssignmentRegisteredEvent(
        ChatEscalationAssignmentId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatEscalationAssignmentRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
