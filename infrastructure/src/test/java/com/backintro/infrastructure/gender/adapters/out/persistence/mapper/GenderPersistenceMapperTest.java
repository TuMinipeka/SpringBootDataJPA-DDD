package com.backintro.infrastructure.gender.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.gender.model.aggregate.Gender;

class GenderPersistenceMapperTest {

    private final GenderPersistenceMapper mapper =
            new GenderPersistenceMapper();

    @Test
    void mapsGenderInBothDirectionsWithoutCreatingDomainEvents() {
        Gender original = Gender.register("Female");

        Gender restored = mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.description()).isEqualTo("Female");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
