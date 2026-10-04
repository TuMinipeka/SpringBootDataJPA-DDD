package com.backintro.application.riskassessment.usecase;

import java.time.LocalDateTime;

import com.backintro.application.riskassessment.exception.RiskAssessmentNotFoundApplicationException;
import com.backintro.domain.riskassessment.event.RiskAssessmentDeletedEvent;
import com.backintro.domain.riskassessment.model.valueobject.RiskAssessmentId;
import com.backintro.domain.riskassessment.port.repository.RiskAssessmentRepository;

public class DeleteRiskAssessmentUseCase {

    private final RiskAssessmentRepository riskAssessmentRepository;

    public DeleteRiskAssessmentUseCase(
            RiskAssessmentRepository riskAssessmentRepository
    ) {
        this.riskAssessmentRepository = riskAssessmentRepository;
    }

    public RiskAssessmentDeletedEvent execute(RiskAssessmentId id) {
        var riskAssessment = riskAssessmentRepository.findById(id)
                .orElseThrow(() ->
                        new RiskAssessmentNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        riskAssessmentRepository.delete(riskAssessment);

        return new RiskAssessmentDeletedEvent(id, LocalDateTime.now());
    }
}
