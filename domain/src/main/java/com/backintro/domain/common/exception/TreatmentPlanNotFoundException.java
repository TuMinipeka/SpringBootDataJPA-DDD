package com.backintro.domain.common.exception;

import com.backintro.domain.treatmentplan.model.valueobject.TreatmentPlanId;

public class TreatmentPlanNotFoundException extends RuntimeException {

    public TreatmentPlanNotFoundException(TreatmentPlanId id) {
        super("Treatment plan not found with id: " + id.value());
    }
}
