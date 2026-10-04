package com.backintro.application.encounterstatus.exception;

import com.backintro.application.common.exception.ApplicationException;

public class EncounterStatusNotFoundApplicationException
        extends ApplicationException {

    public EncounterStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
