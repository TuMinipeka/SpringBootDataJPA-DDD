package com.backintro.infrastructure.chatescalation.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.chatescalation.model.aggregate.ChatEscalation;
import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.backintro.infrastructure.chatescalation.adapters.out.persistence.entity.ChatEscalationJpaEntity;

@Component
public class ChatEscalationPersistenceMapper {

    public ChatEscalation toDomain(ChatEscalationJpaEntity entity) {
        return ChatEscalation.restore(
                new ChatEscalationId(entity.getId()),
                new ChatConversationId(entity.getConversationId()),
                new EscalationStatusId(entity.getStatusId()),
                entity.isFromAi(),
                entity.getReason()
        );
    }

    public ChatEscalationJpaEntity toNewEntity(ChatEscalation escalation) {
        return new ChatEscalationJpaEntity(
                escalation.id().value(),
                escalation.conversationId().value(),
                escalation.statusId().value(),
                escalation.fromAi(),
                escalation.reason()
        );
    }

    public void synchronize(
            ChatEscalation escalation,
            ChatEscalationJpaEntity entity
    ) {
        entity.synchronize(
                escalation.statusId().value(),
                escalation.fromAi(),
                escalation.reason()
        );
    }
}
