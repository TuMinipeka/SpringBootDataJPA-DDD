package com.backintro.application.chatairun.command;

import java.util.Objects;

import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;

public record RegisterChatAiRunCommand(
        ChatConversationId conversationId,
        ChatMessageId messageId,
        AiModelId modelId,
        AiRunStatusId aiRunStatusId
) {

    public RegisterChatAiRunCommand {
        Objects.requireNonNull(
                conversationId,
                "conversationId must not be null"
        );
        Objects.requireNonNull(modelId, "modelId must not be null");
        Objects.requireNonNull(
                aiRunStatusId,
                "aiRunStatusId must not be null"
        );
    }
}
