package com.backintro.infrastructure.patient.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.domain.gender.model.valueobject.GenderId;
import com.backintro.domain.patient.model.aggregate.Patient;

class PatientPersistenceMapperTest {

    private final PatientPersistenceMapper mapper =
            new PatientPersistenceMapper();

    @Test
    void mapsPatientInBothDirectionsWithoutCreatingDomainEvents() {
        DocumentTypeId documentTypeId =
                new DocumentTypeId(UUID.randomUUID());
        GenderId biologicalSexId = new GenderId(UUID.randomUUID());
        GenderId genderIdentityId = new GenderId(UUID.randomUUID());
        CityMunicipalityId cityId =
                new CityMunicipalityId(UUID.randomUUID());
        Patient original = Patient.register(
                documentTypeId,
                "123456789",
                "Jane",
                "Marie",
                "Doe",
                "Smith",
                LocalDate.of(1990, 1, 15),
                biologicalSexId,
                genderIdentityId,
                "jane@example.com",
                "+57 300 123 4567",
                "Main Street 10",
                cityId
        );

        Patient restored = mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.documentTypeId()).isEqualTo(documentTypeId);
        assertThat(restored.documentNumber()).isEqualTo("123456789");
        assertThat(restored.firstName()).isEqualTo("Jane");
        assertThat(restored.middleName()).isEqualTo("Marie");
        assertThat(restored.lastName()).isEqualTo("Doe");
        assertThat(restored.secondLastName()).isEqualTo("Smith");
        assertThat(restored.birthDate()).isEqualTo(LocalDate.of(1990, 1, 15));
        assertThat(restored.biologicalSexId()).isEqualTo(biologicalSexId);
        assertThat(restored.genderIdentityId()).isEqualTo(genderIdentityId);
        assertThat(restored.email()).isEqualTo("jane@example.com");
        assertThat(restored.phone()).isEqualTo("+57 300 123 4567");
        assertThat(restored.address()).isEqualTo("Main Street 10");
        assertThat(restored.active()).isTrue();
        assertThat(restored.cityId()).isEqualTo(cityId);
        assertThat(restored.domainEvents()).isEmpty();
    }
}
