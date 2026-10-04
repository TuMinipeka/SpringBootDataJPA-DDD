package com.backintro.infrastructure.citymunicipality.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.backintro.domain.citymunicipality.model.aggregate.CityMunicipality;
import com.backintro.domain.stateregion.model.valueobject.StateRegionId;

class CityMunicipalityPersistenceMapperTest {

    private final CityMunicipalityPersistenceMapper mapper =
            new CityMunicipalityPersistenceMapper();

    @Test
    void mapsCityMunicipalityInBothDirectionsWithoutCreatingDomainEvents() {
        StateRegionId regionId = new StateRegionId(UUID.randomUUID());
        CityMunicipality original = CityMunicipality.register(
                regionId,
                "Medellin",
                "MDE"
        );

        CityMunicipality restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.regionId()).isEqualTo(regionId);
        assertThat(restored.nameCity()).isEqualTo("Medellin");
        assertThat(restored.codeCity()).isEqualTo("MDE");
        assertThat(restored.active()).isTrue();
        assertThat(restored.domainEvents()).isEmpty();
    }
}
