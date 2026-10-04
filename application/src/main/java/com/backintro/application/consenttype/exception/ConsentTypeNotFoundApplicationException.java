package com.backintro.application.consenttype.exception;

import com.backintro.application.common.exception.ApplicationException;

public class ConsentTypeNotFoundApplicationException
        extends ApplicationException {

    public ConsentTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
