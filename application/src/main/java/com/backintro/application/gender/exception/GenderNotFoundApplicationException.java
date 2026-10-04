package com.backintro.application.gender.exception;

import com.backintro.application.common.exception.ApplicationException;

public class GenderNotFoundApplicationException extends ApplicationException {

    public GenderNotFoundApplicationException(String message) {
        super(message);
    }
}
