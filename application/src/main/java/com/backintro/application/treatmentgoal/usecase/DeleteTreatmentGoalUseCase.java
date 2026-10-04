package com.backintro.application.treatmentgoal.usecase;

import java.time.LocalDateTime;

import com.backintro.application.treatmentgoal.exception.TreatmentGoalNotFoundApplicationException;
import com.backintro.domain.treatmentgoal.event.TreatmentGoalDeletedEvent;
import com.backintro.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import com.backintro.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

public class DeleteTreatmentGoalUseCase {

    private final TreatmentGoalRepository treatmentGoalRepository;

    public DeleteTreatmentGoalUseCase(
            TreatmentGoalRepository treatmentGoalRepository
    ) {
        this.treatmentGoalRepository = treatmentGoalRepository;
    }

    public TreatmentGoalDeletedEvent execute(TreatmentGoalId id) {
        var treatmentGoal = treatmentGoalRepository.findById(id)
                .orElseThrow(() ->
                        new TreatmentGoalNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        treatmentGoalRepository.delete(treatmentGoal);

        return new TreatmentGoalDeletedEvent(id, LocalDateTime.now());
    }
}
