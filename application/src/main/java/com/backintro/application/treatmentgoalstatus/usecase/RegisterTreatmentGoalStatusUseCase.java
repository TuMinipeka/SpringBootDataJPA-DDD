package com.backintro.application.treatmentgoalstatus.usecase;

import com.backintro.application.treatmentgoalstatus.command.RegisterTreatmentGoalStatusCommand;
import com.backintro.application.treatmentgoalstatus.dto.TreatmentGoalStatusResponse;
import com.backintro.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import com.backintro.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class RegisterTreatmentGoalStatusUseCase {

    private final TreatmentGoalStatusRepository treatmentGoalStatusRepository;

    public RegisterTreatmentGoalStatusUseCase(
            TreatmentGoalStatusRepository treatmentGoalStatusRepository
    ) {
        this.treatmentGoalStatusRepository = treatmentGoalStatusRepository;
    }

    public TreatmentGoalStatusResponse execute(
            RegisterTreatmentGoalStatusCommand command
    ) {
        TreatmentGoalStatus treatmentGoalStatus =
                TreatmentGoalStatus.register(
                        command.name(),
                        command.code()
                );

        TreatmentGoalStatus saved =
                treatmentGoalStatusRepository.save(treatmentGoalStatus);

        return new TreatmentGoalStatusResponse(
                saved.id().value(),
                saved.name(),
                saved.code()
        );
    }
}
