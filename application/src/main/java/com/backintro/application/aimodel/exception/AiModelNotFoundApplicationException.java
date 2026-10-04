package com.backintro.application.aimodel.exception;

import com.backintro.application.common.exception.ApplicationException;

public class AiModelNotFoundApplicationException
        extends ApplicationException {

    public AiModelNotFoundApplicationException(String message) {
        super(message);
    }
}
