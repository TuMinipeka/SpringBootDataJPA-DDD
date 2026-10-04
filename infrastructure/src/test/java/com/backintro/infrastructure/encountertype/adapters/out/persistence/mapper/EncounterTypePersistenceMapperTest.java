package com.backintro.infrastructure.encountertype.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.encountertype.model.aggregate.EncounterType;

class EncounterTypePersistenceMapperTest {

    private final EncounterTypePersistenceMapper mapper =
            new EncounterTypePersistenceMapper();

    @Test
    void mapsEncounterTypeInBothDirectionsWithoutCreatingDomainEvents() {
        EncounterType original = EncounterType.register(
                "Initial Consultation",
                "INITIAL"
        );

        EncounterType restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.name()).isEqualTo("Initial Consultation");
        assertThat(restored.code()).isEqualTo("INITIAL");
        assertThat(restored.active()).isTrue();
        assertThat(restored.domainEvents()).isEmpty();
    }
}
