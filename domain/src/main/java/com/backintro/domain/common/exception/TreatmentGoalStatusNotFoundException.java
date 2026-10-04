package com.backintro.domain.common.exception;

import com.backintro.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

public class TreatmentGoalStatusNotFoundException extends RuntimeException {

    public TreatmentGoalStatusNotFoundException(TreatmentGoalStatusId id) {
        super("Treatment goal status not found with id: " + id.value());
    }
}
