package com.backintro.application.treatmentgoalstatus.usecase;

import java.util.List;

import com.backintro.application.treatmentgoalstatus.dto.TreatmentGoalStatusResponse;
import com.backintro.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class ListTreatmentGoalStatusUseCase {

    private final TreatmentGoalStatusRepository treatmentGoalStatusRepository;

    public ListTreatmentGoalStatusUseCase(
            TreatmentGoalStatusRepository treatmentGoalStatusRepository
    ) {
        this.treatmentGoalStatusRepository = treatmentGoalStatusRepository;
    }

    public List<TreatmentGoalStatusResponse> execute() {
        return treatmentGoalStatusRepository.findAll()
                .stream()
                .map(treatmentGoalStatus ->
                        new TreatmentGoalStatusResponse(
                                treatmentGoalStatus.id().value(),
                                treatmentGoalStatus.name(),
                                treatmentGoalStatus.code()
                        )
                )
                .toList();
    }
}
