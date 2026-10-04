package com.backintro.domain.medicationroute.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.medicationroute.model.valueobject.MedicationRouteId;

public record MedicationRouteUpdatedEvent(
        MedicationRouteId id,
        String name,
        String code,
        LocalDateTime occurredOn
) implements DomainEvent {

    public MedicationRouteUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
