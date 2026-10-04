package com.backintro.application.treatmentgoal.usecase;

import com.backintro.application.treatmentgoal.command.UpdateTreatmentGoalCommand;
import com.backintro.application.treatmentgoal.dto.TreatmentGoalResponse;
import com.backintro.application.treatmentgoal.exception.TreatmentGoalNotFoundApplicationException;
import com.backintro.application.treatmentgoalstatus.exception.TreatmentGoalStatusNotFoundApplicationException;
import com.backintro.domain.treatmentgoal.port.repository.TreatmentGoalRepository;
import com.backintro.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class UpdateTreatmentGoalUseCase {

    private final TreatmentGoalRepository treatmentGoalRepository;
    private final TreatmentGoalStatusRepository statusRepository;

    public UpdateTreatmentGoalUseCase(
            TreatmentGoalRepository treatmentGoalRepository,
            TreatmentGoalStatusRepository statusRepository
    ) {
        this.treatmentGoalRepository = treatmentGoalRepository;
        this.statusRepository = statusRepository;
    }

    public TreatmentGoalResponse execute(UpdateTreatmentGoalCommand command) {
        var treatmentGoal = treatmentGoalRepository.findById(command.id())
                .orElseThrow(() ->
                        new TreatmentGoalNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );
        statusRepository.findById(command.statusId())
                .orElseThrow(() ->
                        new TreatmentGoalStatusNotFoundApplicationException(
                                command.statusId().value().toString()
                        )
                );

        treatmentGoal.update(
                command.description(),
                command.targetDate(),
                command.completedAt(),
                command.notes(),
                command.statusId()
        );

        var updated = treatmentGoalRepository.save(treatmentGoal);

        return new TreatmentGoalResponse(
                updated.id().value(),
                updated.description(),
                updated.targetDate(),
                updated.completedAt(),
                updated.notes()
        );
    }
}
