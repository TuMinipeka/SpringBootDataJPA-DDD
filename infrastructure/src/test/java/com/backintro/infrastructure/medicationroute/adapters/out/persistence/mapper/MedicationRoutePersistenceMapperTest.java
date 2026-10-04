package com.backintro.infrastructure.medicationroute.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.medicationroute.model.aggregate.MedicationRoute;

class MedicationRoutePersistenceMapperTest {

    private final MedicationRoutePersistenceMapper mapper =
            new MedicationRoutePersistenceMapper();

    @Test
    void mapsMedicationRouteInBothDirectionsWithoutCreatingDomainEvents() {
        MedicationRoute original = MedicationRoute.register("Oral", "PO");

        MedicationRoute restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.name()).isEqualTo("Oral");
        assertThat(restored.code()).isEqualTo("PO");
        assertThat(restored.active()).isTrue();
        assertThat(restored.domainEvents()).isEmpty();
    }
}
