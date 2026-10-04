package com.backintro.application.encounter.exception;

import com.backintro.application.common.exception.ApplicationException;

public class EncounterNotFoundApplicationException
        extends ApplicationException {

    public EncounterNotFoundApplicationException(String message) {
        super(message);
    }
}
