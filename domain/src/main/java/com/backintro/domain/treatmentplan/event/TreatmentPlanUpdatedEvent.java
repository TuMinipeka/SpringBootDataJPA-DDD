package com.backintro.domain.treatmentplan.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.backintro.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public record TreatmentPlanUpdatedEvent(
        TreatmentPlanId id,
        String title,
        String description,
        LocalDate endDate,
        TreatmentStatusId statusId,
        LocalDateTime occurredOn
) implements DomainEvent {

    public TreatmentPlanUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
