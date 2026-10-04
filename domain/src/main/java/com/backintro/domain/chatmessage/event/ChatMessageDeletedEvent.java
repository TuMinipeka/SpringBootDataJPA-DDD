package com.backintro.domain.chatmessage.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;

public record ChatMessageDeletedEvent(
        ChatMessageId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatMessageDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
