package com.backintro.domain.treatmentplan.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.treatmentplan.model.valueobject.TreatmentPlanId;

public record TreatmentPlanDeletedEvent(
        TreatmentPlanId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public TreatmentPlanDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
