package com.backintro.domain.common.exception;

import com.backintro.domain.treatmentgoal.model.valueobject.TreatmentGoalId;

public class TreatmentGoalNotFoundException extends RuntimeException {

    public TreatmentGoalNotFoundException(TreatmentGoalId id) {
        super("Treatment goal not found with id: " + id.value());
    }
}
