package com.backintro.domain.chatairun.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.common.event.DomainEvent;

public record ChatAiRunDeletedEvent(
        ChatAiRunId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatAiRunDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
