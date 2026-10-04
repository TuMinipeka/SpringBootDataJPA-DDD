package com.backintro.infrastructure.treatmentstatus.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.treatmentstatus.model.aggregate.TreatmentStatus;

class TreatmentStatusPersistenceMapperTest {

    private final TreatmentStatusPersistenceMapper mapper =
            new TreatmentStatusPersistenceMapper();

    @Test
    void mapsTreatmentStatusInBothDirectionsWithoutCreatingDomainEvents() {
        TreatmentStatus original = TreatmentStatus.register(
                "Active",
                "ACTIVE"
        );

        TreatmentStatus restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.name()).isEqualTo("Active");
        assertThat(restored.code()).isEqualTo("ACTIVE");
        assertThat(restored.active()).isTrue();
        assertThat(restored.domainEvents()).isEmpty();
    }
}
