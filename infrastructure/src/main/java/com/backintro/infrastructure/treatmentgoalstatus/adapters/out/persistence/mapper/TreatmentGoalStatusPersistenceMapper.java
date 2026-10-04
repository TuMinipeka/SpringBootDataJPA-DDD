package com.backintro.infrastructure.treatmentgoalstatus.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import com.backintro.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.backintro.infrastructure.treatmentgoalstatus.adapters.out.persistence.entity.TreatmentGoalStatusJpaEntity;

@Component
public class TreatmentGoalStatusPersistenceMapper {

    public TreatmentGoalStatus toDomain(
            TreatmentGoalStatusJpaEntity entity
    ) {
        return TreatmentGoalStatus.restore(
                new TreatmentGoalStatusId(entity.getId()),
                entity.getName(),
                entity.getCode(),
                entity.isActive()
        );
    }

    public TreatmentGoalStatusJpaEntity toNewEntity(
            TreatmentGoalStatus treatmentGoalStatus
    ) {
        return new TreatmentGoalStatusJpaEntity(
                treatmentGoalStatus.id().value(),
                treatmentGoalStatus.name(),
                treatmentGoalStatus.code(),
                treatmentGoalStatus.active()
        );
    }

    public void synchronize(
            TreatmentGoalStatus treatmentGoalStatus,
            TreatmentGoalStatusJpaEntity entity
    ) {
        entity.synchronize(
                treatmentGoalStatus.name(),
                treatmentGoalStatus.code(),
                treatmentGoalStatus.active()
        );
    }
}
