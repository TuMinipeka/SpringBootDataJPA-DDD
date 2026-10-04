package com.backintro.domain.common.exception;

import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;

public class ChatMessageNotFoundException extends RuntimeException {

    public ChatMessageNotFoundException(ChatMessageId id) {
        super("Chat message not found with id: " + id.value());
    }
}
