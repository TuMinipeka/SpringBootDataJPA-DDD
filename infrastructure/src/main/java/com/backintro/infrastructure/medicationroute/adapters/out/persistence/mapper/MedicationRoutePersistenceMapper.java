package com.backintro.infrastructure.medicationroute.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.medicationroute.model.aggregate.MedicationRoute;
import com.backintro.domain.medicationroute.model.valueobject.MedicationRouteId;
import com.backintro.infrastructure.medicationroute.adapters.out.persistence.entity.MedicationRouteJpaEntity;

@Component
public class MedicationRoutePersistenceMapper {

    public MedicationRoute toDomain(MedicationRouteJpaEntity entity) {
        return MedicationRoute.restore(
                new MedicationRouteId(entity.getId()),
                entity.getName(),
                entity.getCode(),
                entity.isActive()
        );
    }

    public MedicationRouteJpaEntity toNewEntity(
            MedicationRoute medicationRoute
    ) {
        return new MedicationRouteJpaEntity(
                medicationRoute.id().value(),
                medicationRoute.name(),
                medicationRoute.code(),
                medicationRoute.active()
        );
    }

    public void synchronize(
            MedicationRoute medicationRoute,
            MedicationRouteJpaEntity entity
    ) {
        entity.synchronize(
                medicationRoute.name(),
                medicationRoute.code(),
                medicationRoute.active()
        );
    }
}
