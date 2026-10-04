package com.backintro.application.treatmentplan.usecase;

import java.time.LocalDateTime;

import com.backintro.application.treatmentplan.exception.TreatmentPlanNotFoundApplicationException;
import com.backintro.domain.treatmentplan.event.TreatmentPlanDeletedEvent;
import com.backintro.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.backintro.domain.treatmentplan.port.repository.TreatmentPlanRepository;

public class DeleteTreatmentPlanUseCase {

    private final TreatmentPlanRepository treatmentPlanRepository;

    public DeleteTreatmentPlanUseCase(
            TreatmentPlanRepository treatmentPlanRepository
    ) {
        this.treatmentPlanRepository = treatmentPlanRepository;
    }

    public TreatmentPlanDeletedEvent execute(TreatmentPlanId id) {
        var treatmentPlan = treatmentPlanRepository.findById(id)
                .orElseThrow(() ->
                        new TreatmentPlanNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        treatmentPlanRepository.delete(treatmentPlan);

        return new TreatmentPlanDeletedEvent(id, LocalDateTime.now());
    }
}
