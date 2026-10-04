package com.backintro.domain.chatairunerror.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import com.backintro.domain.common.event.DomainEvent;

public record ChatAiRunErrorUpdatedEvent(
        ChatAiRunErrorId id,
        String errorMessage,
        String errorCode,
        String providerErrorId,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatAiRunErrorUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(errorMessage, "errorMessage must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
