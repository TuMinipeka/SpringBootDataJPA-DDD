package com.backintro.domain.common.exception;

import com.backintro.domain.messagetype.model.valueobject.MessageTypeId;

public class MessageTypeNotFoundException extends RuntimeException {

    public MessageTypeNotFoundException(MessageTypeId id) {
        super("Message type not found with id: " + id.value());
    }
}
