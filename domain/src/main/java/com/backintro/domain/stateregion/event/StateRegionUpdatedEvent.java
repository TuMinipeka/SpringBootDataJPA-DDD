package com.backintro.domain.stateregion.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.stateregion.model.valueobject.StateRegionId;

public record StateRegionUpdatedEvent(
        StateRegionId id,
        String nameRegion,
        String codeRegion,
        LocalDateTime occurredOn
) implements DomainEvent {

    public StateRegionUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameRegion, "nameRegion must not be null");
        Objects.requireNonNull(codeRegion, "codeRegion must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
