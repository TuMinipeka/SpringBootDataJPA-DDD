package com.backintro.domain.encountertype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.encountertype.model.valueobject.EncounterTypeId;

public record EncounterTypeDeletedEvent(
        EncounterTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public EncounterTypeDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
