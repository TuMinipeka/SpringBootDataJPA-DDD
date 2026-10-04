package com.backintro.infrastructure.phonecontact.adapters.out.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "phone_contacts",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_phone_contacts_contact_phone",
                columnNames = {"contact_id", "phone"}
        )
)
public class PhoneContactJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "contact_id", nullable = false, updatable = false)
    private UUID contactId;

    @Column(name = "phone", nullable = false, length = 30)
    private String phone;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    protected PhoneContactJpaEntity() {
        // Required by JPA.
    }

    public PhoneContactJpaEntity(
            UUID id,
            UUID contactId,
            String phone,
            String notes
    ) {
        this.id = id;
        this.contactId = contactId;
        this.phone = phone;
        this.notes = notes;
    }

    public void synchronize(String phone, String notes) {
        this.phone = phone;
        this.notes = notes;
    }

    public UUID getId() {
        return id;
    }

    public UUID getContactId() {
        return contactId;
    }

    public String getPhone() {
        return phone;
    }

    public String getNotes() {
        return notes;
    }
}
