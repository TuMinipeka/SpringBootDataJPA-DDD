package com.backintro.application.treatmentgoal.usecase;

import com.backintro.application.treatmentgoal.dto.TreatmentGoalResponse;
import com.backintro.application.treatmentgoal.exception.TreatmentGoalNotFoundApplicationException;
import com.backintro.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import com.backintro.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

public class GetTreatmentGoalByIdUseCase {

    private final TreatmentGoalRepository treatmentGoalRepository;

    public GetTreatmentGoalByIdUseCase(
            TreatmentGoalRepository treatmentGoalRepository
    ) {
        this.treatmentGoalRepository = treatmentGoalRepository;
    }

    public TreatmentGoalResponse execute(TreatmentGoalId id) {
        var treatmentGoal = treatmentGoalRepository.findById(id)
                .orElseThrow(() ->
                        new TreatmentGoalNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new TreatmentGoalResponse(
                treatmentGoal.id().value(),
                treatmentGoal.description(),
                treatmentGoal.targetDate(),
                treatmentGoal.completedAt(),
                treatmentGoal.notes()
        );
    }
}
