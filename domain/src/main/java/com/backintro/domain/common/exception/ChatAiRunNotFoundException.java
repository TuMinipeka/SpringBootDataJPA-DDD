package com.backintro.domain.common.exception;

import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;

public class ChatAiRunNotFoundException extends RuntimeException {

    public ChatAiRunNotFoundException(ChatAiRunId id) {
        super("Chat AI run not found with id: " + id.value());
    }
}
