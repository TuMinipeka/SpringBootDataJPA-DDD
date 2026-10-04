package com.backintro.infrastructure.encountermodality.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.encountermodality.model.aggregate.EncounterModality;
import com.backintro.domain.encountermodality.model.valueobject.EncounterModalityId;
import com.backintro.infrastructure.encountermodality.adapters.out.persistence.entity.EncounterModalityJpaEntity;

@Component
public class EncounterModalityPersistenceMapper {

    public EncounterModality toDomain(EncounterModalityJpaEntity entity) {
        return EncounterModality.restore(
                new EncounterModalityId(entity.getId()),
                entity.getName(),
                entity.getCode(),
                entity.isActive()
        );
    }

    public EncounterModalityJpaEntity toNewEntity(
            EncounterModality encounterModality
    ) {
        return new EncounterModalityJpaEntity(
                encounterModality.id().value(),
                encounterModality.name(),
                encounterModality.code(),
                encounterModality.active()
        );
    }

    public void synchronize(
            EncounterModality encounterModality,
            EncounterModalityJpaEntity entity
    ) {
        entity.synchronize(
                encounterModality.name(),
                encounterModality.code(),
                encounterModality.active()
        );
    }
}
