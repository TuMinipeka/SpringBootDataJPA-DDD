package com.backintro.application.messagetype.exception;

import com.backintro.application.common.exception.ApplicationException;

public class MessageTypeNotFoundApplicationException
        extends ApplicationException {

    public MessageTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
