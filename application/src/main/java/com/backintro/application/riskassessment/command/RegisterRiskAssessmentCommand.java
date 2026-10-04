package com.backintro.application.riskassessment.command;

import java.util.Objects;

import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.risklevel.model.valueobject.RiskLevelId;

public record RegisterRiskAssessmentCommand(
        EncounterId encounterId,
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
        ProfessionalId assessedBy
) {

    public RegisterRiskAssessmentCommand {
        Objects.requireNonNull(encounterId, "encounterId must not be null");
        Objects.requireNonNull(riskLevelId, "riskLevelId must not be null");
        Objects.requireNonNull(assessedBy, "assessedBy must not be null");
    }
}
