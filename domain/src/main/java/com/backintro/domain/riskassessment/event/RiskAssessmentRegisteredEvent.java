package com.backintro.domain.riskassessment.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.riskassessment.model.valueobject.RiskAssessmentId;

public record RiskAssessmentRegisteredEvent(
        RiskAssessmentId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public RiskAssessmentRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
