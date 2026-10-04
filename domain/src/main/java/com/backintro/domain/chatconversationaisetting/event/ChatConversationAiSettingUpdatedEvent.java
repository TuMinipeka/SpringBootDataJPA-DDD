package com.backintro.domain.chatconversationaisetting.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import com.backintro.domain.common.event.DomainEvent;

public record ChatConversationAiSettingUpdatedEvent(
        ChatConversationAiSettingId id,
        boolean aiEnabled,
        AiModelId defaultModelId,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatConversationAiSettingUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
