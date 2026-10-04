package com.backintro.domain.common.exception;

import com.backintro.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;

public class ChatAiRunErrorNotFoundException extends RuntimeException {

    public ChatAiRunErrorNotFoundException(ChatAiRunErrorId id) {
        super("Chat AI run error not found with id: " + id.value());
    }
}
