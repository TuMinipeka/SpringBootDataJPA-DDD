package com.backintro.infrastructure.stateregion.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.backintro.domain.country.model.valueobject.CountryId;
import com.backintro.domain.stateregion.model.aggregate.StateRegion;

class StateRegionPersistenceMapperTest {

    private final StateRegionPersistenceMapper mapper =
            new StateRegionPersistenceMapper();

    @Test
    void mapsStateRegionInBothDirectionsWithoutCreatingDomainEvents() {
        CountryId countryId = new CountryId(UUID.randomUUID());
        StateRegion original = StateRegion.register(
                countryId,
                "Antioquia",
                "ANT"
        );

        StateRegion restored = mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.countryId()).isEqualTo(countryId);
        assertThat(restored.nameRegion()).isEqualTo("Antioquia");
        assertThat(restored.codeRegion()).isEqualTo("ANT");
        assertThat(restored.active()).isTrue();
        assertThat(restored.domainEvents()).isEmpty();
    }
}
