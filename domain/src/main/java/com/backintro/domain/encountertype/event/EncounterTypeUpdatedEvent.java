package com.backintro.domain.encountertype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.encountertype.model.valueobject.EncounterTypeId;

public record EncounterTypeUpdatedEvent(
        EncounterTypeId id,
        String name,
        String code,
        LocalDateTime occurredOn
) implements DomainEvent {

    public EncounterTypeUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
