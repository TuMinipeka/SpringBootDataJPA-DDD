package com.backintro.application.treatmentgoalstatus.usecase;

import java.time.LocalDateTime;

import com.backintro.application.treatmentgoalstatus.exception.TreatmentGoalStatusNotFoundApplicationException;
import com.backintro.domain.treatmentgoalstatus.event.TreatmentGoalStatusDeletedEvent;
import com.backintro.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.backintro.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class DeleteTreatmentGoalStatusUseCase {

    private final TreatmentGoalStatusRepository treatmentGoalStatusRepository;

    public DeleteTreatmentGoalStatusUseCase(
            TreatmentGoalStatusRepository treatmentGoalStatusRepository
    ) {
        this.treatmentGoalStatusRepository = treatmentGoalStatusRepository;
    }

    public TreatmentGoalStatusDeletedEvent execute(TreatmentGoalStatusId id) {
        var treatmentGoalStatus = treatmentGoalStatusRepository.findById(id)
                .orElseThrow(() ->
                        new TreatmentGoalStatusNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        treatmentGoalStatusRepository.delete(treatmentGoalStatus);

        return new TreatmentGoalStatusDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
