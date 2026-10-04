package com.backintro.infrastructure.riskassessment.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record UpdateRiskAssessmentRequest(

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

        String observations

) {
}
