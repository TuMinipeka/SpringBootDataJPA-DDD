package com.backintro.application.patientallergy.exception;

import com.backintro.application.common.exception.ApplicationException;

public class PatientAllergyNotFoundApplicationException
        extends ApplicationException {

    public PatientAllergyNotFoundApplicationException(String message) {
        super(message);
    }
}
