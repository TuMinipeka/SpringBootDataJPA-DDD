package com.backintro.application.treatmentgoal.usecase;

import com.backintro.application.treatmentgoal.command.RegisterTreatmentGoalCommand;
import com.backintro.application.treatmentgoal.dto.TreatmentGoalResponse;
import com.backintro.application.treatmentgoalstatus.exception.TreatmentGoalStatusNotFoundApplicationException;
import com.backintro.application.treatmentplan.exception.TreatmentPlanNotFoundApplicationException;
import com.backintro.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import com.backintro.domain.treatmentgoal.port.repository.TreatmentGoalRepository;
import com.backintro.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;
import com.backintro.domain.treatmentplan.port.repository.TreatmentPlanRepository;

public class RegisterTreatmentGoalUseCase {

    private final TreatmentGoalRepository treatmentGoalRepository;
    private final TreatmentPlanRepository treatmentPlanRepository;
    private final TreatmentGoalStatusRepository statusRepository;

    public RegisterTreatmentGoalUseCase(
            TreatmentGoalRepository treatmentGoalRepository,
            TreatmentPlanRepository treatmentPlanRepository,
            TreatmentGoalStatusRepository statusRepository
    ) {
        this.treatmentGoalRepository = treatmentGoalRepository;
        this.treatmentPlanRepository = treatmentPlanRepository;
        this.statusRepository = statusRepository;
    }

    public TreatmentGoalResponse execute(
            RegisterTreatmentGoalCommand command
    ) {
        treatmentPlanRepository.findById(command.treatmentPlanId())
                .orElseThrow(() ->
                        new TreatmentPlanNotFoundApplicationException(
                                command.treatmentPlanId().value().toString()
                        )
                );
        statusRepository.findById(command.statusId())
                .orElseThrow(() ->
                        new TreatmentGoalStatusNotFoundApplicationException(
                                command.statusId().value().toString()
                        )
                );

        TreatmentGoal treatmentGoal = TreatmentGoal.register(
                command.treatmentPlanId(),
                command.description(),
                command.targetDate(),
                command.notes(),
                command.statusId()
        );

        return toResponse(treatmentGoalRepository.save(treatmentGoal));
    }

    private TreatmentGoalResponse toResponse(TreatmentGoal treatmentGoal) {
        return new TreatmentGoalResponse(
                treatmentGoal.id().value(),
                treatmentGoal.description(),
                treatmentGoal.targetDate(),
                treatmentGoal.completedAt(),
                treatmentGoal.notes()
        );
    }
}
