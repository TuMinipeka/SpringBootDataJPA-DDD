package com.backintro.infrastructure.phonecontact.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.phonecontact.model.aggregate.PhoneContact;

class PhoneContactPersistenceMapperTest {

    private final PhoneContactPersistenceMapper mapper =
            new PhoneContactPersistenceMapper();

    @Test
    void mapsPhoneContactInBothDirectionsWithoutCreatingDomainEvents() {
        ContactId contactId = new ContactId(UUID.randomUUID());
        PhoneContact original = PhoneContact.register(
                contactId,
                "+57 300 123 4567",
                "Mobile"
        );

        PhoneContact restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.contactId()).isEqualTo(contactId);
        assertThat(restored.phone()).isEqualTo("+57 300 123 4567");
        assertThat(restored.notes()).isEqualTo("Mobile");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
