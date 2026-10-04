package com.backintro.domain.common.exception;

import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;

public class ChatConversationNotFoundException extends RuntimeException {

    public ChatConversationNotFoundException(ChatConversationId id) {
        super("Chat conversation not found with id: " + id.value());
    }
}
