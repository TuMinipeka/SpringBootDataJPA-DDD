package com.backintro.domain.common.exception;

import com.backintro.domain.patient.model.valueobject.PatientId;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(PatientId id) {
        super("Patient not found with id: " + id.value());
    }
}
