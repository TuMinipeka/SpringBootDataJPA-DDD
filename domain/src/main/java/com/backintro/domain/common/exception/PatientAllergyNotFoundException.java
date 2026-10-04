package com.backintro.domain.common.exception;

import com.backintro.domain.patientallergy.model.valueobject.PatientAllergyId;

public class PatientAllergyNotFoundException extends RuntimeException {

    public PatientAllergyNotFoundException(PatientAllergyId id) {
        super("Patient allergy not found with id: " + id.value());
    }
}
