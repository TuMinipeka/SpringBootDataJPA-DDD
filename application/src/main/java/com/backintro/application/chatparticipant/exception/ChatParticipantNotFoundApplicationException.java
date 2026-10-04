package com.backintro.application.chatparticipant.exception;

import com.backintro.application.common.exception.ApplicationException;

public class ChatParticipantNotFoundApplicationException
        extends ApplicationException {

    public ChatParticipantNotFoundApplicationException(String message) {
        super(message);
    }
}
