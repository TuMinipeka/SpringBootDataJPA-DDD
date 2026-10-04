package com.backintro.domain.chatparticipant.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.chatparticipant.model.valueobject.ChatParticipantId;

public record ChatParticipantRegisteredEvent(
        ChatParticipantId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatParticipantRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
