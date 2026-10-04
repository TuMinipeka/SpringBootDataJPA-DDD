package com.backintro.application.chatconversation.exception;

import com.backintro.application.common.exception.ApplicationException;

public class ChatConversationNotFoundApplicationException
        extends ApplicationException {

    public ChatConversationNotFoundApplicationException(String message) {
        super(message);
    }
}
