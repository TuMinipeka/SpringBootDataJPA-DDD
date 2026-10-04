package com.backintro.infrastructure.encounterstatus.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.encounterstatus.model.aggregate.EncounterStatus;

class EncounterStatusPersistenceMapperTest {

    private final EncounterStatusPersistenceMapper mapper =
            new EncounterStatusPersistenceMapper();

    @Test
    void mapsEncounterStatusInBothDirectionsWithoutCreatingDomainEvents() {
        EncounterStatus original = EncounterStatus.register(
                "Scheduled",
                "SCHEDULED"
        );

        EncounterStatus restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.name()).isEqualTo("Scheduled");
        assertThat(restored.code()).isEqualTo("SCHEDULED");
        assertThat(restored.active()).isTrue();
        assertThat(restored.domainEvents()).isEmpty();
    }
}
