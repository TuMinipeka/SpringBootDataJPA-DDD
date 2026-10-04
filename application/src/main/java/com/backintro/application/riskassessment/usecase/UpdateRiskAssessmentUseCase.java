package com.backintro.application.riskassessment.usecase;

import com.backintro.application.riskassessment.command.UpdateRiskAssessmentCommand;
import com.backintro.application.riskassessment.dto.RiskAssessmentResponse;
import com.backintro.application.riskassessment.exception.RiskAssessmentNotFoundApplicationException;
import com.backintro.application.risklevel.exception.RiskLevelNotFoundApplicationException;
import com.backintro.domain.riskassessment.model.aggregate.RiskAssessment;
import com.backintro.domain.riskassessment.port.repository.RiskAssessmentRepository;
import com.backintro.domain.risklevel.port.repository.RiskLevelRepository;

public class UpdateRiskAssessmentUseCase {

    private final RiskAssessmentRepository riskAssessmentRepository;
    private final RiskLevelRepository riskLevelRepository;

    public UpdateRiskAssessmentUseCase(
            RiskAssessmentRepository riskAssessmentRepository,
            RiskLevelRepository riskLevelRepository
    ) {
        this.riskAssessmentRepository = riskAssessmentRepository;
        this.riskLevelRepository = riskLevelRepository;
    }

    public RiskAssessmentResponse execute(
            UpdateRiskAssessmentCommand command
    ) {
        var riskAssessment = riskAssessmentRepository.findById(command.id())
                .orElseThrow(() ->
                        new RiskAssessmentNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );
        riskLevelRepository.findById(command.riskLevelId())
                .orElseThrow(() ->
                        new RiskLevelNotFoundApplicationException(
                                command.riskLevelId().value().toString()
                        )
                );

        riskAssessment.update(
                command.riskLevelId(),
                command.suicidalIdeation(),
                command.suicidePlan(),
                command.suicideIntent(),
                command.selfHarm(),
                command.harmToOthers(),
                command.riskFactors(),
                command.protectiveFactors(),
                command.clinicalActions(),
                command.observations()
        );

        return toResponse(riskAssessmentRepository.save(riskAssessment));
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
