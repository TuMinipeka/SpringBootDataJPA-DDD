package com.backintro.infrastructure.encountertype.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.encountertype.model.aggregate.EncounterType;
import com.backintro.domain.encountertype.model.valueobject.EncounterTypeId;
import com.backintro.infrastructure.encountertype.adapters.out.persistence.entity.EncounterTypeJpaEntity;

@Component
public class EncounterTypePersistenceMapper {

    public EncounterType toDomain(EncounterTypeJpaEntity entity) {
        return EncounterType.restore(
                new EncounterTypeId(entity.getId()),
                entity.getName(),
                entity.getCode(),
                entity.isActive()
        );
    }

    public EncounterTypeJpaEntity toNewEntity(EncounterType encounterType) {
        return new EncounterTypeJpaEntity(
                encounterType.id().value(),
                encounterType.name(),
                encounterType.code(),
                encounterType.active()
        );
    }

    public void synchronize(
            EncounterType encounterType,
            EncounterTypeJpaEntity entity
    ) {
        entity.synchronize(
                encounterType.name(),
                encounterType.code(),
                encounterType.active()
        );
    }
}
