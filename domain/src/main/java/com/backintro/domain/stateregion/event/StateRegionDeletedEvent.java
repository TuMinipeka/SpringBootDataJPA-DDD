package com.backintro.domain.stateregion.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.stateregion.model.valueobject.StateRegionId;

public record StateRegionDeletedEvent(
        StateRegionId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public StateRegionDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
