package com.backintro.application.chatairunerror.command;

import java.util.Objects;

import com.backintro.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;

public record UpdateChatAiRunErrorCommand(
        ChatAiRunErrorId id,
        String errorMessage,
        String errorCode,
        String providerErrorId
) {

    public UpdateChatAiRunErrorCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(errorMessage, "errorMessage must not be null");
    }
}
