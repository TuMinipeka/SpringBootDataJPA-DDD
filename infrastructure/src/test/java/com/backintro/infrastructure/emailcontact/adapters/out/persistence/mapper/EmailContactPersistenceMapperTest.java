package com.backintro.infrastructure.emailcontact.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.emailcontact.model.aggregate.EmailContact;

class EmailContactPersistenceMapperTest {

    private final EmailContactPersistenceMapper mapper =
            new EmailContactPersistenceMapper();

    @Test
    void mapsEmailContactInBothDirectionsWithoutCreatingDomainEvents() {
        ContactId contactId = new ContactId(UUID.randomUUID());
        EmailContact original = EmailContact.register(
                contactId,
                "jane@example.com",
                "Personal"
        );

        EmailContact restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.contactId()).isEqualTo(contactId);
        assertThat(restored.email()).isEqualTo("jane@example.com");
        assertThat(restored.notes()).isEqualTo("Personal");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
