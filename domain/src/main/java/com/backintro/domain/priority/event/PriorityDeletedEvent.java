package com.backintro.domain.priority.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.priority.model.valueobject.PriorityId;

public record PriorityDeletedEvent(
        PriorityId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public PriorityDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
