package com.backintro.infrastructure.patientcontact.adapters.out.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "patient_contacts",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_patient_contacts_patient_contact",
                columnNames = {"patient_id", "contact_id"}
        )
)
public class PatientContactJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "contact_id", nullable = false, updatable = false)
    private UUID contactId;

    @Column(name = "patient_id", nullable = false, updatable = false)
    private UUID patientId;

    @Column(name = "is_primary_contact", nullable = false)
    private boolean primaryContact;

    @Column(name = "is_emergency_contact", nullable = false)
    private boolean emergencyContact;

    @Column(name = "relationship_type_id", nullable = false, updatable = false)
    private UUID relationshipTypeId;

    protected PatientContactJpaEntity() {
        // Required by JPA.
    }

    public PatientContactJpaEntity(
            UUID id,
            UUID contactId,
            UUID patientId,
            boolean primaryContact,
            boolean emergencyContact,
            UUID relationshipTypeId
    ) {
        this.id = id;
        this.contactId = contactId;
        this.patientId = patientId;
        this.primaryContact = primaryContact;
        this.emergencyContact = emergencyContact;
        this.relationshipTypeId = relationshipTypeId;
    }

    public void synchronize(
            boolean primaryContact,
            boolean emergencyContact
    ) {
        this.primaryContact = primaryContact;
        this.emergencyContact = emergencyContact;
    }

    public UUID getId() {
        return id;
    }

    public UUID getContactId() {
        return contactId;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public boolean isPrimaryContact() {
        return primaryContact;
    }

    public boolean isEmergencyContact() {
        return emergencyContact;
    }

    public UUID getRelationshipTypeId() {
        return relationshipTypeId;
    }
}
