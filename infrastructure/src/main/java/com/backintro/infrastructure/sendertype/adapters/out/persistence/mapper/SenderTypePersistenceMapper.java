package com.backintro.infrastructure.sendertype.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.sendertype.model.aggregate.SenderType;
import com.backintro.domain.sendertype.model.valueobject.SenderTypeId;
import com.backintro.infrastructure.sendertype.adapters.out.persistence.entity.SenderTypeJpaEntity;

@Component
public class SenderTypePersistenceMapper {

    public SenderType toDomain(SenderTypeJpaEntity entity) {
        return SenderType.restore(
                new SenderTypeId(entity.getId()),
                entity.getNameType()
        );
    }

    public SenderTypeJpaEntity toNewEntity(
            SenderType senderType
    ) {
        return new SenderTypeJpaEntity(
                senderType.id().value(),
                senderType.nameType()
        );
    }

    public void synchronize(
            SenderType senderType,
            SenderTypeJpaEntity entity
    ) {
        entity.synchronize(senderType.nameType());
    }
}
