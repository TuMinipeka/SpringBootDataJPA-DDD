package com.backintro.domain.patient.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.patient.model.valueobject.PatientId;

public record PatientDeletedEvent(
        PatientId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public PatientDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
