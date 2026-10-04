package com.backintro.domain.escalationstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;

public record EscalationStatusDeletedEvent(
        EscalationStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public EscalationStatusDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
