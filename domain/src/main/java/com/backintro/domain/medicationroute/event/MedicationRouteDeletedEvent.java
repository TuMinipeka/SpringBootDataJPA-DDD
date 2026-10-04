package com.backintro.domain.medicationroute.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.medicationroute.model.valueobject.MedicationRouteId;

public record MedicationRouteDeletedEvent(
        MedicationRouteId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public MedicationRouteDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
