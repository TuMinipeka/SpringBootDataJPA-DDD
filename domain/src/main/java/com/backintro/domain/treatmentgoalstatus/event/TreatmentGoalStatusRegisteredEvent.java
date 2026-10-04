package com.backintro.domain.treatmentgoalstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

public record TreatmentGoalStatusRegisteredEvent(
        TreatmentGoalStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public TreatmentGoalStatusRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
