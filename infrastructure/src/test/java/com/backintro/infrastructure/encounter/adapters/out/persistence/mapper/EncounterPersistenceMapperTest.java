package com.backintro.infrastructure.encounter.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.backintro.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.backintro.domain.encounter.model.aggregate.Encounter;
import com.backintro.domain.encountermodality.model.valueobject.EncounterModalityId;
import com.backintro.domain.encounterstatus.model.valueobject.EncounterStatusId;
import com.backintro.domain.encountertype.model.valueobject.EncounterTypeId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

class EncounterPersistenceMapperTest {

    private final EncounterPersistenceMapper mapper =
            new EncounterPersistenceMapper();

    @Test
    void mapsEncounterInBothDirectionsWithoutCreatingDomainEvents() {
        ClinicalRecordId clinicalRecordId =
                new ClinicalRecordId(UUID.randomUUID());
        ProfessionalId professionalId =
                new ProfessionalId(UUID.randomUUID());
        EncounterTypeId encounterTypeId =
                new EncounterTypeId(UUID.randomUUID());
        EncounterModalityId modalityId =
                new EncounterModalityId(UUID.randomUUID());
        EncounterStatusId statusId =
                new EncounterStatusId(UUID.randomUUID());
        LocalDateTime startedAt = LocalDateTime.of(2026, 10, 3, 9, 0);
        Encounter original = Encounter.register(
                clinicalRecordId,
                professionalId,
                encounterTypeId,
                startedAt,
                "Routine follow-up",
                "Stable",
                modalityId,
                statusId
        );

        Encounter restored = mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.clinicalRecordId()).isEqualTo(clinicalRecordId);
        assertThat(restored.professionalId()).isEqualTo(professionalId);
        assertThat(restored.encounterTypeId()).isEqualTo(encounterTypeId);
        assertThat(restored.startedAt()).isEqualTo(startedAt);
        assertThat(restored.endedAt()).isNull();
        assertThat(restored.reasonForVisit()).isEqualTo("Routine follow-up");
        assertThat(restored.currentCondition()).isEqualTo("Stable");
        assertThat(restored.modalityId()).isEqualTo(modalityId);
        assertThat(restored.statusId()).isEqualTo(statusId);
        assertThat(restored.domainEvents()).isEmpty();
    }
}
