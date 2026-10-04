package com.backintro.infrastructure.patientallergy.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.patientallergy.model.aggregate.PatientAllergy;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

class PatientAllergyPersistenceMapperTest {

    private final PatientAllergyPersistenceMapper mapper =
            new PatientAllergyPersistenceMapper();

    @Test
    void mapsAllergyInBothDirectionsWithoutCreatingDomainEvents() {
        PatientAllergy original = PatientAllergy.register(
                PatientId.generate(),
                "Penicillin",
                "Skin rash",
                "MODERATE",
                ProfessionalId.generate()
        );

        PatientAllergy restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.patientId()).isEqualTo(original.patientId());
        assertThat(restored.substance()).isEqualTo("Penicillin");
        assertThat(restored.reaction()).isEqualTo("Skin rash");
        assertThat(restored.severity()).isEqualTo("MODERATE");
        assertThat(restored.active()).isTrue();
        assertThat(restored.recordedBy()).isEqualTo(original.recordedBy());
        assertThat(restored.domainEvents()).isEmpty();
    }
}
