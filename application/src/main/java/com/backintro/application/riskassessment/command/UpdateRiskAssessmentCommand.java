package com.backintro.application.riskassessment.command;

import java.util.Objects;

import com.backintro.domain.riskassessment.model.valueobject.RiskAssessmentId;
import com.backintro.domain.risklevel.model.valueobject.RiskLevelId;

public record UpdateRiskAssessmentCommand(
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
        String observations
) {

    public UpdateRiskAssessmentCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(riskLevelId, "riskLevelId must not be null");
    }
}
