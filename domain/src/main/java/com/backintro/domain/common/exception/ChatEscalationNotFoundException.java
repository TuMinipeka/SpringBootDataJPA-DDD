package com.backintro.domain.common.exception;

import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;

public class ChatEscalationNotFoundException extends RuntimeException {

    public ChatEscalationNotFoundException(ChatEscalationId id) {
        super("Chat escalation not found with id: " + id.value());
    }
}
