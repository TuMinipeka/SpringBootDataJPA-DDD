package com.backintro.application.riskassessment.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record RiskAssessmentResponse(
        UUID id,
        boolean suicidalIdeation,
        boolean suicidePlan,
        boolean suicideIntent,
        boolean selfHarm,
        boolean harmToOthers,
        String riskFactors,
        String protectiveFactors,
        String clinicalActions,
        String observations,
        LocalDateTime assessedAt
) {
}
