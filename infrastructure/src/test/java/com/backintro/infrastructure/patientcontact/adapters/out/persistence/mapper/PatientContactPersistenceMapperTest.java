package com.backintro.infrastructure.patientcontact.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.patientcontact.model.aggregate.PatientContact;
import com.backintro.domain.relationshiptype.model.valueobject.RelationshipTypeId;

class PatientContactPersistenceMapperTest {

    private final PatientContactPersistenceMapper mapper =
            new PatientContactPersistenceMapper();

    @Test
    void mapsPatientContactInBothDirectionsWithoutCreatingDomainEvents() {
        ContactId contactId = new ContactId(UUID.randomUUID());
        PatientId patientId = new PatientId(UUID.randomUUID());
        RelationshipTypeId relationshipTypeId =
                new RelationshipTypeId(UUID.randomUUID());
        PatientContact original = PatientContact.register(
                contactId,
                patientId,
                true,
                false,
                relationshipTypeId
        );

        PatientContact restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.contactId()).isEqualTo(contactId);
        assertThat(restored.patientId()).isEqualTo(patientId);
        assertThat(restored.primaryContact()).isTrue();
        assertThat(restored.emergencyContact()).isFalse();
        assertThat(restored.relationshipTypeId()).isEqualTo(relationshipTypeId);
        assertThat(restored.domainEvents()).isEmpty();
    }
}
