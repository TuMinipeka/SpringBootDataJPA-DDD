package com.backintro.application.chatescalationassignment.exception;

import com.backintro.application.common.exception.ApplicationException;

public class ChatEscalationAssignmentNotFoundApplicationException
        extends ApplicationException {

    public ChatEscalationAssignmentNotFoundApplicationException(
            String message
    ) {
        super(message);
    }
}
