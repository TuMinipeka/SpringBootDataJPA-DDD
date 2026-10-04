package com.backintro.application.treatmentplan.usecase;

import com.backintro.application.treatmentplan.dto.TreatmentPlanResponse;
import com.backintro.application.treatmentplan.exception.TreatmentPlanNotFoundApplicationException;
import com.backintro.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.backintro.domain.treatmentplan.port.repository.TreatmentPlanRepository;

public class GetTreatmentPlanByIdUseCase {

    private final TreatmentPlanRepository treatmentPlanRepository;

    public GetTreatmentPlanByIdUseCase(
            TreatmentPlanRepository treatmentPlanRepository
    ) {
        this.treatmentPlanRepository = treatmentPlanRepository;
    }

    public TreatmentPlanResponse execute(TreatmentPlanId id) {
        var treatmentPlan = treatmentPlanRepository.findById(id)
                .orElseThrow(() ->
                        new TreatmentPlanNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new TreatmentPlanResponse(
                treatmentPlan.id().value(),
                treatmentPlan.title(),
                treatmentPlan.description(),
                treatmentPlan.startDate(),
                treatmentPlan.endDate()
        );
    }
}
