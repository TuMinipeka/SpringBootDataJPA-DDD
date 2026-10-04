package com.backintro.domain.patientcontact.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.patientcontact.event.PatientContactRegisteredEvent;
import com.backintro.domain.patientcontact.event.PatientContactUpdatedEvent;
import com.backintro.domain.patientcontact.model.valueobject.PatientContactId;
import com.backintro.domain.relationshiptype.model.valueobject.RelationshipTypeId;

public class PatientContact extends AggregateRoot {

    private final PatientContactId id;
    private final ContactId contactId;
    private final PatientId patientId;
    private final RelationshipTypeId relationshipTypeId;
    private boolean primaryContact;
    private boolean emergencyContact;

    private PatientContact(
            PatientContactId id,
            ContactId contactId,
            PatientId patientId,
            boolean primaryContact,
            boolean emergencyContact,
            RelationshipTypeId relationshipTypeId
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.contactId = Objects.requireNonNull(
                contactId,
                "contactId must not be null"
        );
        this.patientId = Objects.requireNonNull(
                patientId,
                "patientId must not be null"
        );
        this.primaryContact = primaryContact;
        this.emergencyContact = emergencyContact;
        this.relationshipTypeId = Objects.requireNonNull(
                relationshipTypeId,
                "relationshipTypeId must not be null"
        );
    }

    public static PatientContact register(
            ContactId contactId,
            PatientId patientId,
            boolean primaryContact,
            boolean emergencyContact,
            RelationshipTypeId relationshipTypeId
    ) {
        PatientContactId id = PatientContactId.generate();
        PatientContact patientContact = new PatientContact(
                id,
                contactId,
                patientId,
                primaryContact,
                emergencyContact,
                relationshipTypeId
        );

        patientContact.recordEvent(
                new PatientContactRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return patientContact;
    }

    public static PatientContact restore(
            PatientContactId id,
            ContactId contactId,
            PatientId patientId,
            boolean primaryContact,
            boolean emergencyContact,
            RelationshipTypeId relationshipTypeId
    ) {
        return new PatientContact(
                id,
                contactId,
                patientId,
                primaryContact,
                emergencyContact,
                relationshipTypeId
        );
    }

    public void update(
            boolean primaryContact,
            boolean emergencyContact
    ) {
        this.primaryContact = primaryContact;
        this.emergencyContact = emergencyContact;

        recordEvent(
                new PatientContactUpdatedEvent(
                        this.id,
                        this.primaryContact,
                        this.emergencyContact,
                        LocalDateTime.now()
                )
        );
    }

    public PatientContactId id() {
        return id;
    }

    public ContactId contactId() {
        return contactId;
    }

    public PatientId patientId() {
        return patientId;
    }

    public boolean primaryContact() {
        return primaryContact;
    }

    public boolean emergencyContact() {
        return emergencyContact;
    }

    public RelationshipTypeId relationshipTypeId() {
        return relationshipTypeId;
    }
}
