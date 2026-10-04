package com.backintro.domain.chatmessage.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;

public record ChatMessageRegisteredEvent(
        ChatMessageId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatMessageRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
