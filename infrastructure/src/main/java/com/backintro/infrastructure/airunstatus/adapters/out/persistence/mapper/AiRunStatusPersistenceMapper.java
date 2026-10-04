package com.backintro.infrastructure.airunstatus.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.airunstatus.model.aggregate.AiRunStatus;
import com.backintro.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.backintro.infrastructure.airunstatus.adapters.out.persistence.entity.AiRunStatusJpaEntity;

@Component
public class AiRunStatusPersistenceMapper {

    public AiRunStatus toDomain(AiRunStatusJpaEntity entity) {
        return AiRunStatus.restore(
                new AiRunStatusId(entity.getId()),
                entity.getNameStatus()
        );
    }

    public AiRunStatusJpaEntity toNewEntity(
            AiRunStatus aiRunStatus
    ) {
        return new AiRunStatusJpaEntity(
                aiRunStatus.id().value(),
                aiRunStatus.nameStatus()
        );
    }

    public void synchronize(
            AiRunStatus aiRunStatus,
            AiRunStatusJpaEntity entity
    ) {
        entity.synchronize(aiRunStatus.nameStatus());
    }
}
