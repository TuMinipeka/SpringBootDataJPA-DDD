package com.backintro.application.treatmentgoal.usecase;

import java.util.List;

import com.backintro.application.treatmentgoal.dto.TreatmentGoalResponse;
import com.backintro.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

public class ListTreatmentGoalUseCase {

    private final TreatmentGoalRepository treatmentGoalRepository;

    public ListTreatmentGoalUseCase(
            TreatmentGoalRepository treatmentGoalRepository
    ) {
        this.treatmentGoalRepository = treatmentGoalRepository;
    }

    public List<TreatmentGoalResponse> execute() {
        return treatmentGoalRepository.findAll()
                .stream()
                .map(treatmentGoal ->
                        new TreatmentGoalResponse(
                                treatmentGoal.id().value(),
                                treatmentGoal.description(),
                                treatmentGoal.targetDate(),
                                treatmentGoal.completedAt(),
                                treatmentGoal.notes()
                        )
                )
                .toList();
    }
}
