package com.backintro.domain.chatairun.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.common.event.DomainEvent;

public record ChatAiRunRegisteredEvent(
        ChatAiRunId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatAiRunRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
