package com.backintro.domain.encounter.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.encounter.model.valueobject.EncounterId;

public record EncounterDeletedEvent(
        EncounterId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public EncounterDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
