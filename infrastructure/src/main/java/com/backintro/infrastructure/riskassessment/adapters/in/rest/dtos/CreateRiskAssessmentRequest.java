package com.backintro.infrastructure.riskassessment.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateRiskAssessmentRequest(

        @NotNull(message = "encounterId is required")
        UUID encounterId,

        @NotNull(message = "riskLevelId is required")
        UUID riskLevelId,

        boolean suicidalIdeation,

        boolean suicidePlan,

        boolean suicideIntent,

        boolean selfHarm,

        boolean harmToOthers,

        String riskFactors,

        String protectiveFactors,

        String clinicalActions,

        String observations,

        @NotNull(message = "assessedBy is required")
        UUID assessedBy

) {
}
