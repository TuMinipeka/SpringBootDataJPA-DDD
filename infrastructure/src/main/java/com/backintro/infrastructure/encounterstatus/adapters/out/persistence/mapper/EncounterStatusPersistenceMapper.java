package com.backintro.infrastructure.encounterstatus.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.encounterstatus.model.aggregate.EncounterStatus;
import com.backintro.domain.encounterstatus.model.valueobject.EncounterStatusId;
import com.backintro.infrastructure.encounterstatus.adapters.out.persistence.entity.EncounterStatusJpaEntity;

@Component
public class EncounterStatusPersistenceMapper {

    public EncounterStatus toDomain(EncounterStatusJpaEntity entity) {
        return EncounterStatus.restore(
                new EncounterStatusId(entity.getId()),
                entity.getName(),
                entity.getCode(),
                entity.isActive()
        );
    }

    public EncounterStatusJpaEntity toNewEntity(
            EncounterStatus encounterStatus
    ) {
        return new EncounterStatusJpaEntity(
                encounterStatus.id().value(),
                encounterStatus.name(),
                encounterStatus.code(),
                encounterStatus.active()
        );
    }

    public void synchronize(
            EncounterStatus encounterStatus,
            EncounterStatusJpaEntity entity
    ) {
        entity.synchronize(
                encounterStatus.name(),
                encounterStatus.code(),
                encounterStatus.active()
        );
    }
}
