package com.backintro.infrastructure.treatmentstatus.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.treatmentstatus.model.aggregate.TreatmentStatus;
import com.backintro.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import com.backintro.infrastructure.treatmentstatus.adapters.out.persistence.entity.TreatmentStatusJpaEntity;

@Component
public class TreatmentStatusPersistenceMapper {

    public TreatmentStatus toDomain(TreatmentStatusJpaEntity entity) {
        return TreatmentStatus.restore(
                new TreatmentStatusId(entity.getId()),
                entity.getName(),
                entity.getCode(),
                entity.isActive()
        );
    }

    public TreatmentStatusJpaEntity toNewEntity(
            TreatmentStatus treatmentStatus
    ) {
        return new TreatmentStatusJpaEntity(
                treatmentStatus.id().value(),
                treatmentStatus.name(),
                treatmentStatus.code(),
                treatmentStatus.active()
        );
    }

    public void synchronize(
            TreatmentStatus treatmentStatus,
            TreatmentStatusJpaEntity entity
    ) {
        entity.synchronize(
                treatmentStatus.name(),
                treatmentStatus.code(),
                treatmentStatus.active()
        );
    }
}
