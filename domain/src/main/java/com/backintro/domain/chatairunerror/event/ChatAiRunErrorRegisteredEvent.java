package com.backintro.domain.chatairunerror.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import com.backintro.domain.common.event.DomainEvent;

public record ChatAiRunErrorRegisteredEvent(
        ChatAiRunErrorId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatAiRunErrorRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
