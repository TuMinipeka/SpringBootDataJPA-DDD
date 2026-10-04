package com.backintro.application.chatairun.command;

import java.util.Objects;

import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;

public record UpdateChatAiRunCommand(
        ChatAiRunId id,
        ChatMessageId messageId,
        AiModelId modelId,
        AiRunStatusId aiRunStatusId
) {

    public UpdateChatAiRunCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(modelId, "modelId must not be null");
        Objects.requireNonNull(
                aiRunStatusId,
                "aiRunStatusId must not be null"
        );
    }
}
