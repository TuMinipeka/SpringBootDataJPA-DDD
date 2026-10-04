package com.backintro.domain.chatconversationaisetting.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;

public record ChatConversationAiSettingDeletedEvent(
        ChatConversationAiSettingId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatConversationAiSettingDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
