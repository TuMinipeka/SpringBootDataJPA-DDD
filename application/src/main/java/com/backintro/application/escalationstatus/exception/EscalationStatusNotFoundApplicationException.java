package com.backintro.application.escalationstatus.exception;

import com.backintro.application.common.exception.ApplicationException;

public class EscalationStatusNotFoundApplicationException
        extends ApplicationException {

    public EscalationStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
