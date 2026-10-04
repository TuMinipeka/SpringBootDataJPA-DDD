package com.backintro.infrastructure.clinicalrecord.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.backintro.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import com.backintro.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.backintro.domain.patient.model.valueobject.PatientId;

class ClinicalRecordPersistenceMapperTest {

    private final ClinicalRecordPersistenceMapper mapper =
            new ClinicalRecordPersistenceMapper();

    @Test
    void mapsClinicalRecordInBothDirectionsWithoutCreatingDomainEvents() {
        PatientId patientId = new PatientId(UUID.randomUUID());
        ClinicalRecordStatusId statusId =
                new ClinicalRecordStatusId(UUID.randomUUID());
        ClinicalRecord original = ClinicalRecord.register(
                patientId,
                "CR-100",
                statusId
        );

        ClinicalRecord restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.patientId()).isEqualTo(patientId);
        assertThat(restored.creationDate()).isEqualTo(original.creationDate());
        assertThat(restored.recordNumber()).isEqualTo("CR-100");
        assertThat(restored.openedAt()).isEqualTo(original.openedAt());
        assertThat(restored.closedAt()).isNull();
        assertThat(restored.statusId()).isEqualTo(statusId);
        assertThat(restored.domainEvents()).isEmpty();
    }
}
