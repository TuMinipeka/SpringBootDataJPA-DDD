package com.backintro.domain.chatconversation.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.backintro.domain.priority.model.valueobject.PriorityId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

public record ChatConversationUpdatedEvent(
        ChatConversationId id,
        ConversationStatusId conversationStatusId,
        PriorityId priorityId,
        LocalDateTime lastMessageAt,
        boolean closed,
        LocalDateTime closedAt,
        ProfessionalId closedBy,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatConversationUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(
                conversationStatusId,
                "conversationStatusId must not be null"
        );
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
