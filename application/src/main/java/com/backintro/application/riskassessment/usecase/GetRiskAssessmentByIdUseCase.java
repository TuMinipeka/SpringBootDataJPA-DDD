package com.backintro.application.riskassessment.usecase;

import com.backintro.application.riskassessment.dto.RiskAssessmentResponse;
import com.backintro.application.riskassessment.exception.RiskAssessmentNotFoundApplicationException;
import com.backintro.domain.riskassessment.model.aggregate.RiskAssessment;
import com.backintro.domain.riskassessment.model.valueobject.RiskAssessmentId;
import com.backintro.domain.riskassessment.port.repository.RiskAssessmentRepository;

public class GetRiskAssessmentByIdUseCase {

    private final RiskAssessmentRepository riskAssessmentRepository;

    public GetRiskAssessmentByIdUseCase(
            RiskAssessmentRepository riskAssessmentRepository
    ) {
        this.riskAssessmentRepository = riskAssessmentRepository;
    }

    public RiskAssessmentResponse execute(RiskAssessmentId id) {
        var riskAssessment = riskAssessmentRepository.findById(id)
                .orElseThrow(() ->
                        new RiskAssessmentNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return toResponse(riskAssessment);
    }

    private RiskAssessmentResponse toResponse(
            RiskAssessment riskAssessment
    ) {
        return new RiskAssessmentResponse(
                riskAssessment.id().value(),
                riskAssessment.suicidalIdeation(),
                riskAssessment.suicidePlan(),
                riskAssessment.suicideIntent(),
                riskAssessment.selfHarm(),
                riskAssessment.harmToOthers(),
                riskAssessment.riskFactors(),
                riskAssessment.protectiveFactors(),
                riskAssessment.clinicalActions(),
                riskAssessment.observations(),
                riskAssessment.assessedAt()
        );
    }
}
