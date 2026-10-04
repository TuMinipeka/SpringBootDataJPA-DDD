package com.backintro.application.priority.exception;

import com.backintro.application.common.exception.ApplicationException;

public class PriorityNotFoundApplicationException
        extends ApplicationException {

    public PriorityNotFoundApplicationException(String message) {
        super(message);
    }
}
