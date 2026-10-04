package com.backintro.infrastructure.encountermodality.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.encountermodality.model.aggregate.EncounterModality;

class EncounterModalityPersistenceMapperTest {

    private final EncounterModalityPersistenceMapper mapper =
            new EncounterModalityPersistenceMapper();

    @Test
    void mapsEncounterModalityInBothDirectionsWithoutCreatingDomainEvents() {
        EncounterModality original = EncounterModality.register(
                "Telemedicine",
                "TELEMEDICINE"
        );

        EncounterModality restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.name()).isEqualTo("Telemedicine");
        assertThat(restored.code()).isEqualTo("TELEMEDICINE");
        assertThat(restored.active()).isTrue();
        assertThat(restored.domainEvents()).isEmpty();
    }
}
