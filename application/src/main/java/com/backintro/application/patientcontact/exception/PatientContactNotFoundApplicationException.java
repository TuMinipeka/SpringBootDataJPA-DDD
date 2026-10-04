package com.backintro.application.patientcontact.exception;

import com.backintro.application.common.exception.ApplicationException;

public class PatientContactNotFoundApplicationException
        extends ApplicationException {

    public PatientContactNotFoundApplicationException(String message) {
        super(message);
    }
}
