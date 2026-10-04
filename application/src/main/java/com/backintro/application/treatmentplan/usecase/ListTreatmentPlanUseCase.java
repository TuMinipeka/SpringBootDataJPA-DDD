package com.backintro.application.treatmentplan.usecase;

import java.util.List;

import com.backintro.application.treatmentplan.dto.TreatmentPlanResponse;
import com.backintro.domain.treatmentplan.port.repository.TreatmentPlanRepository;

public class ListTreatmentPlanUseCase {

    private final TreatmentPlanRepository treatmentPlanRepository;

    public ListTreatmentPlanUseCase(
            TreatmentPlanRepository treatmentPlanRepository
    ) {
        this.treatmentPlanRepository = treatmentPlanRepository;
    }

    public List<TreatmentPlanResponse> execute() {
        return treatmentPlanRepository.findAll()
                .stream()
                .map(treatmentPlan ->
                        new TreatmentPlanResponse(
                                treatmentPlan.id().value(),
                                treatmentPlan.title(),
                                treatmentPlan.description(),
                                treatmentPlan.startDate(),
                                treatmentPlan.endDate()
                        )
                )
                .toList();
    }
}
