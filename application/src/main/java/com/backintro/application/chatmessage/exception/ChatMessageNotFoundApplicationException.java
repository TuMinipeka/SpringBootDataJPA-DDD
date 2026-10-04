package com.backintro.application.chatmessage.exception;

import com.backintro.application.common.exception.ApplicationException;

public class ChatMessageNotFoundApplicationException
        extends ApplicationException {

    public ChatMessageNotFoundApplicationException(String message) {
        super(message);
    }
}
