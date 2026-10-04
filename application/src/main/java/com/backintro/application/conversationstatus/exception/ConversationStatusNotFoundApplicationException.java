package com.backintro.application.conversationstatus.exception;

import com.backintro.application.common.exception.ApplicationException;

public class ConversationStatusNotFoundApplicationException
        extends ApplicationException {

    public ConversationStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
