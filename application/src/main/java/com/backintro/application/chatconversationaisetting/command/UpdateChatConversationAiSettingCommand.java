package com.backintro.application.chatconversationaisetting.command;

import java.util.Objects;

import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;

public record UpdateChatConversationAiSettingCommand(
        ChatConversationAiSettingId id,
        boolean aiEnabled,
        AiModelId defaultModelId
) {

    public UpdateChatConversationAiSettingCommand {
        Objects.requireNonNull(id, "id must not be null");
    }
}
