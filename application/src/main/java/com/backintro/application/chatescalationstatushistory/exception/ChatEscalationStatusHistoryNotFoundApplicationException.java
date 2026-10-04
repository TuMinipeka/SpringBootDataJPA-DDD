package com.backintro.application.chatescalationstatushistory.exception;

import com.backintro.application.common.exception.ApplicationException;

public class ChatEscalationStatusHistoryNotFoundApplicationException
        extends ApplicationException {

    public ChatEscalationStatusHistoryNotFoundApplicationException(
            String message
    ) {
        super(message);
    }
}
