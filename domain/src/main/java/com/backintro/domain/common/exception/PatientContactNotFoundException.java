package com.backintro.domain.common.exception;

import com.backintro.domain.patientcontact.model.valueobject.PatientContactId;

public class PatientContactNotFoundException extends RuntimeException {

    public PatientContactNotFoundException(PatientContactId id) {
        super("Patient contact not found with id: " + id.value());
    }
}
