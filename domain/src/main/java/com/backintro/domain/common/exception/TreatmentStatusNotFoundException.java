package com.backintro.domain.common.exception;

import com.backintro.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public class TreatmentStatusNotFoundException extends RuntimeException {

    public TreatmentStatusNotFoundException(TreatmentStatusId id) {
        super("Treatment status not found with id: " + id.value());
    }
}
