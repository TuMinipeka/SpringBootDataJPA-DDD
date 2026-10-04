package com.backintro.domain.encounterstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.encounterstatus.model.valueobject.EncounterStatusId;

public record EncounterStatusUpdatedEvent(
        EncounterStatusId id,
        String name,
        String code,
        LocalDateTime occurredOn
) implements DomainEvent {

    public EncounterStatusUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
