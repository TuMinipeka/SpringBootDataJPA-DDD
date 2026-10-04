package com.backintro.infrastructure.escalationstatus.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.escalationstatus.model.aggregate.EscalationStatus;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.backintro.infrastructure.escalationstatus.adapters.out.persistence.entity.EscalationStatusJpaEntity;

@Component
public class EscalationStatusPersistenceMapper {

    public EscalationStatus toDomain(EscalationStatusJpaEntity entity) {
        return EscalationStatus.restore(
                new EscalationStatusId(entity.getId()),
                entity.getNameStatus()
        );
    }

    public EscalationStatusJpaEntity toNewEntity(
            EscalationStatus escalationStatus
    ) {
        return new EscalationStatusJpaEntity(
                escalationStatus.id().value(),
                escalationStatus.nameStatus()
        );
    }

    public void synchronize(
            EscalationStatus escalationStatus,
            EscalationStatusJpaEntity entity
    ) {
        entity.synchronize(escalationStatus.nameStatus());
    }
}
