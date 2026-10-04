package com.backintro.domain.escalationstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;

public record EscalationStatusUpdatedEvent(
        EscalationStatusId id,
        String nameStatus,
        LocalDateTime occurredOn
) implements DomainEvent {

    public EscalationStatusUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameStatus, "nameStatus must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
