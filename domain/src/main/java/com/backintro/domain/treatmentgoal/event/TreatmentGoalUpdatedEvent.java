package com.backintro.domain.treatmentgoal.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import com.backintro.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

public record TreatmentGoalUpdatedEvent(
        TreatmentGoalId id,
        String description,
        LocalDate targetDate,
        LocalDateTime completedAt,
        String notes,
        TreatmentGoalStatusId statusId,
        LocalDateTime occurredOn
) implements DomainEvent {

    public TreatmentGoalUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(description, "description must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
