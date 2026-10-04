package com.backintro.domain.common.exception;

import com.backintro.domain.riskassessment.model.valueobject.RiskAssessmentId;

public class RiskAssessmentNotFoundException extends RuntimeException {

    public RiskAssessmentNotFoundException(RiskAssessmentId id) {
        super("Risk assessment not found with id: " + id.value());
    }
}
