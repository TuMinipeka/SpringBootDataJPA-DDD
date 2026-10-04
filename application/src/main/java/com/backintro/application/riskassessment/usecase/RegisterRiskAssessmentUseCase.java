package com.backintro.application.riskassessment.usecase;

import com.backintro.application.encounter.exception.EncounterNotFoundApplicationException;
import com.backintro.application.professional.exception.ProfessionalNotFoundApplicationException;
import com.backintro.application.riskassessment.command.RegisterRiskAssessmentCommand;
import com.backintro.application.riskassessment.dto.RiskAssessmentResponse;
import com.backintro.application.risklevel.exception.RiskLevelNotFoundApplicationException;
import com.backintro.domain.encounter.port.repository.EncounterRepository;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;
import com.backintro.domain.riskassessment.model.aggregate.RiskAssessment;
import com.backintro.domain.riskassessment.port.repository.RiskAssessmentRepository;
import com.backintro.domain.risklevel.port.repository.RiskLevelRepository;

public class RegisterRiskAssessmentUseCase {

    private final RiskAssessmentRepository riskAssessmentRepository;
    private final EncounterRepository encounterRepository;
    private final RiskLevelRepository riskLevelRepository;
    private final ProfessionalRepository professionalRepository;

    public RegisterRiskAssessmentUseCase(
            RiskAssessmentRepository riskAssessmentRepository,
            EncounterRepository encounterRepository,
            RiskLevelRepository riskLevelRepository,
            ProfessionalRepository professionalRepository
    ) {
        this.riskAssessmentRepository = riskAssessmentRepository;
        this.encounterRepository = encounterRepository;
        this.riskLevelRepository = riskLevelRepository;
        this.professionalRepository = professionalRepository;
    }

    public RiskAssessmentResponse execute(
            RegisterRiskAssessmentCommand command
    ) {
        encounterRepository.findById(command.encounterId())
                .orElseThrow(() ->
                        new EncounterNotFoundApplicationException(
                                command.encounterId().value().toString()
                        )
                );
        riskLevelRepository.findById(command.riskLevelId())
                .orElseThrow(() ->
                        new RiskLevelNotFoundApplicationException(
                                command.riskLevelId().value().toString()
                        )
                );
        professionalRepository.findById(command.assessedBy())
                .orElseThrow(() ->
                        new ProfessionalNotFoundApplicationException(
                                command.assessedBy().value().toString()
                        )
                );

        RiskAssessment riskAssessment = RiskAssessment.register(
                command.encounterId(),
                command.riskLevelId(),
                command.suicidalIdeation(),
                command.suicidePlan(),
                command.suicideIntent(),
                command.selfHarm(),
                command.harmToOthers(),
                command.riskFactors(),
                command.protectiveFactors(),
                command.clinicalActions(),
                command.observations(),
                command.assessedBy()
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
