package com.backintro.domain.assessmenttype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import com.backintro.domain.common.event.DomainEvent;

public record AssessmentTypeUpdatedEvent(
        AssessmentTypeId id,
        String name,
        String code,
        String description,
        LocalDateTime occurredOn
) implements DomainEvent {

    public AssessmentTypeUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
