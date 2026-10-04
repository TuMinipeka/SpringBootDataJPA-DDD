package com.backintro.application.stateregion.exception;

import com.backintro.application.common.exception.ApplicationException;

public class StateRegionNotFoundApplicationException extends ApplicationException {

    public StateRegionNotFoundApplicationException(String message) {
        super(message);
    }
}
