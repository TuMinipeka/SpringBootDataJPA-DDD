package com.backintro.infrastructure.messagetype.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.messagetype.model.aggregate.MessageType;
import com.backintro.domain.messagetype.model.valueobject.MessageTypeId;
import com.backintro.infrastructure.messagetype.adapters.out.persistence.entity.MessageTypeJpaEntity;

@Component
public class MessageTypePersistenceMapper {

    public MessageType toDomain(MessageTypeJpaEntity entity) {
        return MessageType.restore(
                new MessageTypeId(entity.getId()),
                entity.getNameType()
        );
    }

    public MessageTypeJpaEntity toNewEntity(
            MessageType messageType
    ) {
        return new MessageTypeJpaEntity(
                messageType.id().value(),
                messageType.nameType()
        );
    }

    public void synchronize(
            MessageType messageType,
            MessageTypeJpaEntity entity
    ) {
        entity.synchronize(messageType.nameType());
    }
}
