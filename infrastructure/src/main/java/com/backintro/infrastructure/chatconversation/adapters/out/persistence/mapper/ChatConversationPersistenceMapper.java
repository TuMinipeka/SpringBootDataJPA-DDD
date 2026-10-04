package com.backintro.infrastructure.chatconversation.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.chatconversation.model.aggregate.ChatConversation;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.backintro.domain.priority.model.valueobject.PriorityId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.infrastructure.chatconversation.adapters.out.persistence.entity.ChatConversationJpaEntity;

@Component
public class ChatConversationPersistenceMapper {

    public ChatConversation toDomain(ChatConversationJpaEntity entity) {
        return ChatConversation.restore(
                new ChatConversationId(entity.getId()),
                new ConversationStatusId(entity.getConversationStatusId()),
                entity.getPriorityId() == null
                        ? null
                        : new PriorityId(entity.getPriorityId()),
                entity.getLastMessageAt(),
                entity.isClosed(),
                entity.getClosedAt(),
                entity.getClosedBy() == null
                        ? null
                        : new ProfessionalId(entity.getClosedBy())
        );
    }

    public ChatConversationJpaEntity toNewEntity(
            ChatConversation chatConversation
    ) {
        return new ChatConversationJpaEntity(
                chatConversation.id().value(),
                chatConversation.conversationStatusId().value(),
                chatConversation.priorityId() == null
                        ? null
                        : chatConversation.priorityId().value(),
                chatConversation.lastMessageAt(),
                chatConversation.closed(),
                chatConversation.closedAt(),
                chatConversation.closedBy() == null
                        ? null
                        : chatConversation.closedBy().value()
        );
    }

    public void synchronize(
            ChatConversation chatConversation,
            ChatConversationJpaEntity entity
    ) {
        entity.synchronize(
                chatConversation.conversationStatusId().value(),
                chatConversation.priorityId() == null
                        ? null
                        : chatConversation.priorityId().value(),
                chatConversation.lastMessageAt(),
                chatConversation.closed(),
                chatConversation.closedAt(),
                chatConversation.closedBy() == null
                        ? null
                        : chatConversation.closedBy().value()
        );
    }
}
