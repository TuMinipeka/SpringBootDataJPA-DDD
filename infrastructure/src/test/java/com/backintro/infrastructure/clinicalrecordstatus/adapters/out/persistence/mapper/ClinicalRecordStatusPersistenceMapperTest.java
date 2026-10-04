package com.backintro.infrastructure.clinicalrecordstatus.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;

class ClinicalRecordStatusPersistenceMapperTest {

    private final ClinicalRecordStatusPersistenceMapper mapper =
            new ClinicalRecordStatusPersistenceMapper();

    @Test
    void mapsClinicalRecordStatusInBothDirectionsWithoutDomainEvents() {
        ClinicalRecordStatus original = ClinicalRecordStatus.register(
                "Open",
                "OPEN"
        );

        ClinicalRecordStatus restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.name()).isEqualTo("Open");
        assertThat(restored.code()).isEqualTo("OPEN");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
