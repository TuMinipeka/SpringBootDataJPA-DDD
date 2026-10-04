package com.backintro.application.treatmentgoalstatus.usecase;

import com.backintro.application.treatmentgoalstatus.command.UpdateTreatmentGoalStatusCommand;
import com.backintro.application.treatmentgoalstatus.dto.TreatmentGoalStatusResponse;
import com.backintro.application.treatmentgoalstatus.exception.TreatmentGoalStatusNotFoundApplicationException;
import com.backintro.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class UpdateTreatmentGoalStatusUseCase {

    private final TreatmentGoalStatusRepository treatmentGoalStatusRepository;

    public UpdateTreatmentGoalStatusUseCase(
            TreatmentGoalStatusRepository treatmentGoalStatusRepository
    ) {
        this.treatmentGoalStatusRepository = treatmentGoalStatusRepository;
    }

    public TreatmentGoalStatusResponse execute(
            UpdateTreatmentGoalStatusCommand command
    ) {
        var treatmentGoalStatus = treatmentGoalStatusRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new TreatmentGoalStatusNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        treatmentGoalStatus.update(command.name(), command.code());

        var updated = treatmentGoalStatusRepository.save(treatmentGoalStatus);

        return new TreatmentGoalStatusResponse(
                updated.id().value(),
                updated.name(),
                updated.code()
        );
    }
}
