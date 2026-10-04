package com.backintro.domain.riskassessment.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.riskassessment.model.valueobject.RiskAssessmentId;
import com.backintro.domain.risklevel.model.valueobject.RiskLevelId;

public record RiskAssessmentUpdatedEvent(
        RiskAssessmentId id,
        RiskLevelId riskLevelId,
        boolean suicidalIdeation,
        boolean suicidePlan,
        boolean suicideIntent,
        boolean selfHarm,
        boolean harmToOthers,
        String riskFactors,
        String protectiveFactors,
        String clinicalActions,
        String observations,
        LocalDateTime occurredOn
) implements DomainEvent {

    public RiskAssessmentUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(riskLevelId, "riskLevelId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
