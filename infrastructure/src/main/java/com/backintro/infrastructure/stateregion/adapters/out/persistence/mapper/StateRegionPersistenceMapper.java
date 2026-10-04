package com.backintro.infrastructure.stateregion.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.country.model.valueobject.CountryId;
import com.backintro.domain.stateregion.model.aggregate.StateRegion;
import com.backintro.domain.stateregion.model.valueobject.StateRegionId;
import com.backintro.infrastructure.stateregion.adapters.out.persistence.entity.StateRegionJpaEntity;

@Component
public class StateRegionPersistenceMapper {

    public StateRegion toDomain(StateRegionJpaEntity entity) {
        return StateRegion.restore(
                new StateRegionId(entity.getId()),
                new CountryId(entity.getCountryId()),
                entity.getNameRegion(),
                entity.getCodeRegion(),
                entity.isActive()
        );
    }

    public StateRegionJpaEntity toNewEntity(StateRegion stateRegion) {
        return new StateRegionJpaEntity(
                stateRegion.id().value(),
                stateRegion.countryId().value(),
                stateRegion.nameRegion(),
                stateRegion.codeRegion(),
                stateRegion.active()
        );
    }

    public void synchronize(
            StateRegion stateRegion,
            StateRegionJpaEntity entity
    ) {
        entity.synchronize(
                stateRegion.nameRegion(),
                stateRegion.codeRegion(),
                stateRegion.active()
        );
    }
}
