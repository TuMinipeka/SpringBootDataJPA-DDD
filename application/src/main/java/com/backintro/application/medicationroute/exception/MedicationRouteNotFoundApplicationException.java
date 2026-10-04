package com.backintro.application.medicationroute.exception;

import com.backintro.application.common.exception.ApplicationException;

public class MedicationRouteNotFoundApplicationException
        extends ApplicationException {

    public MedicationRouteNotFoundApplicationException(String message) {
        super(message);
    }
}
