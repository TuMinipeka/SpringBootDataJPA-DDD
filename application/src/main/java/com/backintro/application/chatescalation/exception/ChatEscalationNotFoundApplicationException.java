package com.backintro.application.chatescalation.exception;

import com.backintro.application.common.exception.ApplicationException;

public class ChatEscalationNotFoundApplicationException
        extends ApplicationException {

    public ChatEscalationNotFoundApplicationException(String message) {
        super(message);
    }
}
