package com.backintro.domain.chatmessage.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;
import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.messagetype.model.valueobject.MessageTypeId;

public record ChatMessageUpdatedEvent(
        ChatMessageId id,
        MessageTypeId messageTypeId,
        String content,
        String metadata,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatMessageUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(
                messageTypeId,
                "messageTypeId must not be null"
        );
        Objects.requireNonNull(content, "content must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
