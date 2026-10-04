package com.backintro.domain.treatmentgoalstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

public record TreatmentGoalStatusUpdatedEvent(
        TreatmentGoalStatusId id,
        String name,
        String code,
        LocalDateTime occurredOn
) implements DomainEvent {

    public TreatmentGoalStatusUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
