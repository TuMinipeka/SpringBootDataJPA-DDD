package com.backintro.infrastructure.clinicalnote.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.backintro.domain.clinicalnote.model.aggregate.ClinicalNote;
import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

class ClinicalNotePersistenceMapperTest {

    private final ClinicalNotePersistenceMapper mapper =
            new ClinicalNotePersistenceMapper();

    @Test
    void mapsClinicalNoteInBothDirectionsWithoutCreatingDomainEvents() {
        EncounterId encounterId = new EncounterId(UUID.randomUUID());
        ProfessionalId professionalId =
                new ProfessionalId(UUID.randomUUID());
        ClinicalNote original = ClinicalNote.register(
                encounterId,
                professionalId,
                "Patient reports improvement",
                "Vital signs stable",
                "Positive evolution",
                "Continue treatment",
                "Follow up in 30 days"
        );

        ClinicalNote restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.encounterId()).isEqualTo(encounterId);
        assertThat(restored.professionalId()).isEqualTo(professionalId);
        assertThat(restored.subjective()).isEqualTo("Patient reports improvement");
        assertThat(restored.objective()).isEqualTo("Vital signs stable");
        assertThat(restored.assessment()).isEqualTo("Positive evolution");
        assertThat(restored.plan()).isEqualTo("Continue treatment");
        assertThat(restored.additionalNotes()).isEqualTo("Follow up in 30 days");
        assertThat(restored.signedAt()).isNull();
        assertThat(restored.domainEvents()).isEmpty();
    }
}
