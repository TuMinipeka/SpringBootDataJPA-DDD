package com.backintro.application.treatmentplan.usecase;

import com.backintro.application.encounter.exception.EncounterNotFoundApplicationException;
import com.backintro.application.professional.exception.ProfessionalNotFoundApplicationException;
import com.backintro.application.treatmentplan.command.RegisterTreatmentPlanCommand;
import com.backintro.application.treatmentplan.dto.TreatmentPlanResponse;
import com.backintro.application.treatmentstatus.exception.TreatmentStatusNotFoundApplicationException;
import com.backintro.domain.encounter.port.repository.EncounterRepository;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;
import com.backintro.domain.treatmentplan.model.aggregate.TreatmentPlan;
import com.backintro.domain.treatmentplan.port.repository.TreatmentPlanRepository;
import com.backintro.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class RegisterTreatmentPlanUseCase {

    private final TreatmentPlanRepository treatmentPlanRepository;
    private final EncounterRepository encounterRepository;
    private final ProfessionalRepository professionalRepository;
    private final TreatmentStatusRepository statusRepository;

    public RegisterTreatmentPlanUseCase(
            TreatmentPlanRepository treatmentPlanRepository,
            EncounterRepository encounterRepository,
            ProfessionalRepository professionalRepository,
            TreatmentStatusRepository statusRepository
    ) {
        this.treatmentPlanRepository = treatmentPlanRepository;
        this.encounterRepository = encounterRepository;
        this.professionalRepository = professionalRepository;
        this.statusRepository = statusRepository;
    }

    public TreatmentPlanResponse execute(
            RegisterTreatmentPlanCommand command
    ) {
        encounterRepository.findById(command.encounterId())
                .orElseThrow(() ->
                        new EncounterNotFoundApplicationException(
                                command.encounterId().value().toString()
                        )
                );
        professionalRepository.findById(command.professionalId())
                .orElseThrow(() ->
                        new ProfessionalNotFoundApplicationException(
                                command.professionalId().value().toString()
                        )
                );
        statusRepository.findById(command.statusId())
                .orElseThrow(() ->
                        new TreatmentStatusNotFoundApplicationException(
                                command.statusId().value().toString()
                        )
                );

        TreatmentPlan treatmentPlan = TreatmentPlan.register(
                command.encounterId(),
                command.professionalId(),
                command.title(),
                command.description(),
                command.startDate(),
                command.statusId()
        );

        return toResponse(treatmentPlanRepository.save(treatmentPlan));
    }

    private TreatmentPlanResponse toResponse(TreatmentPlan treatmentPlan) {
        return new TreatmentPlanResponse(
                treatmentPlan.id().value(),
                treatmentPlan.title(),
                treatmentPlan.description(),
                treatmentPlan.startDate(),
                treatmentPlan.endDate()
        );
    }
}
