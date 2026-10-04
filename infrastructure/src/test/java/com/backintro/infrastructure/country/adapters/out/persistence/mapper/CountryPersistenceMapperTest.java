package com.backintro.infrastructure.country.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.country.model.aggregate.Country;

class CountryPersistenceMapperTest {

    private final CountryPersistenceMapper mapper = new CountryPersistenceMapper();

    @Test
    void mapsCountryInBothDirectionsWithoutCreatingDomainEvents() {
        Country original = Country.register("Colombia", "CO");

        Country restored = mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.name()).isEqualTo("Colombia");
        assertThat(restored.code()).isEqualTo("CO");
        assertThat(restored.active()).isTrue();
        assertThat(restored.domainEvents()).isEmpty();
    }
}
