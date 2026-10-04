package com.backintro.application.chatconversationaisetting.command;

import java.util.Objects;

import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;

public record RegisterChatConversationAiSettingCommand(
        ChatConversationId conversationId,
        AiModelId defaultModelId
) {

    public RegisterChatConversationAiSettingCommand {
        Objects.requireNonNull(
                conversationId,
                "conversationId must not be null"
        );
    }
}
