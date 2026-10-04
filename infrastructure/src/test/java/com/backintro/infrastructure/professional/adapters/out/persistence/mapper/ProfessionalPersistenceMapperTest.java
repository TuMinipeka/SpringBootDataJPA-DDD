package com.backintro.infrastructure.professional.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.domain.professional.model.aggregate.Professional;
import com.backintro.domain.professionaltype.model.valueobject.ProfessionalTypeId;

class ProfessionalPersistenceMapperTest {

    private final ProfessionalPersistenceMapper mapper =
            new ProfessionalPersistenceMapper();

    @Test
    void mapsProfessionalInBothDirectionsWithoutCreatingDomainEvents() {
        DocumentTypeId documentTypeId =
                new DocumentTypeId(UUID.randomUUID());
        ProfessionalTypeId professionalTypeId =
                new ProfessionalTypeId(UUID.randomUUID());
        CityMunicipalityId cityId =
                new CityMunicipalityId(UUID.randomUUID());
        ContactId contactId = new ContactId(UUID.randomUUID());
        Professional original = Professional.register(
                documentTypeId,
                "123456789",
                "Jane",
                "Doe",
                professionalTypeId,
                "LIC-100",
                cityId,
                contactId
        );

        Professional restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.documentTypeId()).isEqualTo(documentTypeId);
        assertThat(restored.documentNumber()).isEqualTo("123456789");
        assertThat(restored.firstName()).isEqualTo("Jane");
        assertThat(restored.lastName()).isEqualTo("Doe");
        assertThat(restored.professionalTypeId()).isEqualTo(professionalTypeId);
        assertThat(restored.licenseNumber()).isEqualTo("LIC-100");
        assertThat(restored.active()).isTrue();
        assertThat(restored.cityId()).isEqualTo(cityId);
        assertThat(restored.contactId()).isEqualTo(contactId);
        assertThat(restored.domainEvents()).isEmpty();
    }
}
