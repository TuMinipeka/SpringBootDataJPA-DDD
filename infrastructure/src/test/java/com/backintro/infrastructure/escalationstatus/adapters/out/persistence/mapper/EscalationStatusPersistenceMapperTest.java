package com.backintro.infrastructure.escalationstatus.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.escalationstatus.model.aggregate.EscalationStatus;

class EscalationStatusPersistenceMapperTest {

    private final EscalationStatusPersistenceMapper mapper =
            new EscalationStatusPersistenceMapper();

    @Test
    void mapsEscalationStatusInBothDirectionsWithoutCreatingDomainEvents() {
        EscalationStatus original = EscalationStatus.register("Open");

        EscalationStatus restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.nameStatus()).isEqualTo("Open");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
