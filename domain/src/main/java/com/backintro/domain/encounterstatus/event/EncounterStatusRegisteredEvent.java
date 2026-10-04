package com.backintro.domain.encounterstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.encounterstatus.model.valueobject.EncounterStatusId;

public record EncounterStatusRegisteredEvent(
        EncounterStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public EncounterStatusRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
