package com.backintro.infrastructure.emailcontact.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "email_contacts",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_email_contacts_contact_email",
                columnNames = {"contact_id", "email"}
        )
)
public class EmailContactJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "contact_id", nullable = false, updatable = false)
    private UUID contactId;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected EmailContactJpaEntity() {
        // Required by JPA.
    }

    public EmailContactJpaEntity(
            UUID id,
            UUID contactId,
            String email,
            String notes
    ) {
        this.id = id;
        this.contactId = contactId;
        this.email = email;
        this.notes = notes;
    }

    public void synchronize(String email, String notes) {
        this.email = email;
        this.notes = notes;
    }

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (createdAt == null) {
            createdAt = now;
        }
        updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public UUID getContactId() {
        return contactId;
    }

    public String getEmail() {
        return email;
    }

    public String getNotes() {
        return notes;
    }
}
