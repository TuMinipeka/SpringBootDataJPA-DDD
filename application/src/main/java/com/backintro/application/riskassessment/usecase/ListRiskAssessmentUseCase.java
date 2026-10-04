package com.backintro.application.riskassessment.usecase;

import java.util.List;

import com.backintro.application.riskassessment.dto.RiskAssessmentResponse;
import com.backintro.domain.riskassessment.model.aggregate.RiskAssessment;
import com.backintro.domain.riskassessment.port.repository.RiskAssessmentRepository;

public class ListRiskAssessmentUseCase {

    private final RiskAssessmentRepository riskAssessmentRepository;

    public ListRiskAssessmentUseCase(
            RiskAssessmentRepository riskAssessmentRepository
    ) {
        this.riskAssessmentRepository = riskAssessmentRepository;
    }

    public List<RiskAssessmentResponse> execute() {
        return riskAssessmentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
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
