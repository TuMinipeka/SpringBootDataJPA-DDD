package com.backintro.application.encountertype.exception;

import com.backintro.application.common.exception.ApplicationException;

public class EncounterTypeNotFoundApplicationException
        extends ApplicationException {

    public EncounterTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
