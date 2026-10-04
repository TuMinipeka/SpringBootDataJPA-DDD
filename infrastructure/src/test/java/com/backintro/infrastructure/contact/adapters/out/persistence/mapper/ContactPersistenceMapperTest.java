package com.backintro.infrastructure.contact.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.contact.model.aggregate.Contact;

class ContactPersistenceMapperTest {

    private final ContactPersistenceMapper mapper =
            new ContactPersistenceMapper();

    @Test
    void mapsContactInBothDirectionsWithoutCreatingDomainEvents() {
        CityMunicipalityId cityId = new CityMunicipalityId(UUID.randomUUID());
        Contact original = Contact.register(
                "Jane Doe",
                "jane@example.com",
                "Primary contact",
                cityId
        );

        Contact restored = mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.fullName()).isEqualTo("Jane Doe");
        assertThat(restored.email()).isEqualTo("jane@example.com");
        assertThat(restored.notes()).isEqualTo("Primary contact");
        assertThat(restored.cityId()).isEqualTo(cityId);
        assertThat(restored.domainEvents()).isEmpty();
    }
}
