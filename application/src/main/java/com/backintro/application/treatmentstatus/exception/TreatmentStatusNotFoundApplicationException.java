package com.backintro.application.treatmentstatus.exception;

import com.backintro.application.common.exception.ApplicationException;

public class TreatmentStatusNotFoundApplicationException
        extends ApplicationException {

    public TreatmentStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
