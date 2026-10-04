package com.backintro.application.treatmentplan.usecase;

import com.backintro.application.treatmentplan.command.UpdateTreatmentPlanCommand;
import com.backintro.application.treatmentplan.dto.TreatmentPlanResponse;
import com.backintro.application.treatmentplan.exception.TreatmentPlanNotFoundApplicationException;
import com.backintro.application.treatmentstatus.exception.TreatmentStatusNotFoundApplicationException;
import com.backintro.domain.treatmentplan.port.repository.TreatmentPlanRepository;
import com.backintro.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class UpdateTreatmentPlanUseCase {

    private final TreatmentPlanRepository treatmentPlanRepository;
    private final TreatmentStatusRepository statusRepository;

    public UpdateTreatmentPlanUseCase(
            TreatmentPlanRepository treatmentPlanRepository,
            TreatmentStatusRepository statusRepository
    ) {
        this.treatmentPlanRepository = treatmentPlanRepository;
        this.statusRepository = statusRepository;
    }

    public TreatmentPlanResponse execute(UpdateTreatmentPlanCommand command) {
        var treatmentPlan = treatmentPlanRepository.findById(command.id())
                .orElseThrow(() ->
                        new TreatmentPlanNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );
        statusRepository.findById(command.statusId())
                .orElseThrow(() ->
                        new TreatmentStatusNotFoundApplicationException(
                                command.statusId().value().toString()
                        )
                );

        treatmentPlan.update(
                command.title(),
                command.description(),
                command.endDate(),
                command.statusId()
        );

        var updated = treatmentPlanRepository.save(treatmentPlan);

        return new TreatmentPlanResponse(
                updated.id().value(),
                updated.title(),
                updated.description(),
                updated.startDate(),
                updated.endDate()
        );
    }
}
