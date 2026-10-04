package com.backintro.application.sendertype.exception;

import com.backintro.application.common.exception.ApplicationException;

public class SenderTypeNotFoundApplicationException
        extends ApplicationException {

    public SenderTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
