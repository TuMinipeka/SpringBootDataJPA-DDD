package com.backintro.infrastructure.citymunicipality.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.citymunicipality.model.aggregate.CityMunicipality;
import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.stateregion.model.valueobject.StateRegionId;
import com.backintro.infrastructure.citymunicipality.adapters.out.persistence.entity.CityMunicipalityJpaEntity;

@Component
public class CityMunicipalityPersistenceMapper {

    public CityMunicipality toDomain(CityMunicipalityJpaEntity entity) {
        return CityMunicipality.restore(
                new CityMunicipalityId(entity.getId()),
                new StateRegionId(entity.getRegionId()),
                entity.getNameCity(),
                entity.getCodeCity(),
                entity.isActive()
        );
    }

    public CityMunicipalityJpaEntity toNewEntity(
            CityMunicipality cityMunicipality
    ) {
        return new CityMunicipalityJpaEntity(
                cityMunicipality.id().value(),
                cityMunicipality.regionId().value(),
                cityMunicipality.nameCity(),
                cityMunicipality.codeCity(),
                cityMunicipality.active()
        );
    }

    public void synchronize(
            CityMunicipality cityMunicipality,
            CityMunicipalityJpaEntity entity
    ) {
        entity.synchronize(
                cityMunicipality.nameCity(),
                cityMunicipality.codeCity(),
                cityMunicipality.active()
        );
    }
}
