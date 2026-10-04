package com.backintro.domain.common.exception;

import com.backintro.domain.conversationstatus.model.valueobject.ConversationStatusId;

public class ConversationStatusNotFoundException extends RuntimeException {

    public ConversationStatusNotFoundException(ConversationStatusId id) {
        super("Conversation status not found with id: " + id.value());
    }
}
