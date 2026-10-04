package com.backintro.application.treatmentgoalstatus.usecase;

import com.backintro.application.treatmentgoalstatus.dto.TreatmentGoalStatusResponse;
import com.backintro.application.treatmentgoalstatus.exception.TreatmentGoalStatusNotFoundApplicationException;
import com.backintro.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.backintro.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class GetTreatmentGoalStatusByIdUseCase {

    private final TreatmentGoalStatusRepository treatmentGoalStatusRepository;

    public GetTreatmentGoalStatusByIdUseCase(
            TreatmentGoalStatusRepository treatmentGoalStatusRepository
    ) {
        this.treatmentGoalStatusRepository = treatmentGoalStatusRepository;
    }

    public TreatmentGoalStatusResponse execute(TreatmentGoalStatusId id) {
        var treatmentGoalStatus = treatmentGoalStatusRepository.findById(id)
                .orElseThrow(() ->
                        new TreatmentGoalStatusNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new TreatmentGoalStatusResponse(
                treatmentGoalStatus.id().value(),
                treatmentGoalStatus.name(),
                treatmentGoalStatus.code()
        );
    }
}
