package com.backintro.application.chatairunerror.command;

import java.util.Objects;

import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;

public record RegisterChatAiRunErrorCommand(
        ChatAiRunId aiRunId,
        String errorMessage,
        String errorCode,
        String providerErrorId
) {

    public RegisterChatAiRunErrorCommand {
        Objects.requireNonNull(aiRunId, "aiRunId must not be null");
        Objects.requireNonNull(errorMessage, "errorMessage must not be null");
    }
}
