package com.backintro.infrastructure.conversationstatus.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.conversationstatus.model.aggregate.ConversationStatus;
import com.backintro.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.backintro.infrastructure.conversationstatus.adapters.out.persistence.entity.ConversationStatusJpaEntity;

@Component
public class ConversationStatusPersistenceMapper {

    public ConversationStatus toDomain(ConversationStatusJpaEntity entity) {
        return ConversationStatus.restore(
                new ConversationStatusId(entity.getId()),
                entity.getNameStatus()
        );
    }

    public ConversationStatusJpaEntity toNewEntity(
            ConversationStatus conversationStatus
    ) {
        return new ConversationStatusJpaEntity(
                conversationStatus.id().value(),
                conversationStatus.nameStatus()
        );
    }

    public void synchronize(
            ConversationStatus conversationStatus,
            ConversationStatusJpaEntity entity
    ) {
        entity.synchronize(conversationStatus.nameStatus());
    }
}
