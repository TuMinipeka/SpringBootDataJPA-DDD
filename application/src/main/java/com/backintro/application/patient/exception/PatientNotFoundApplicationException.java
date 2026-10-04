package com.backintro.application.patient.exception;

import com.backintro.application.common.exception.ApplicationException;

public class PatientNotFoundApplicationException extends ApplicationException {

    public PatientNotFoundApplicationException(String message) {
        super(message);
    }
}
