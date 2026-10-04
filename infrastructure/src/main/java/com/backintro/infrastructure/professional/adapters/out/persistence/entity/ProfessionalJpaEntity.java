package com.backintro.infrastructure.professional.adapters.out.persistence.entity;

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
        name = "professionals",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_professionals_document",
                        columnNames = {"document_type_id", "document_number"}
                ),
                @UniqueConstraint(
                        name = "uk_professionals_license_number",
                        columnNames = {"license_number"}
                )
        }
)
public class ProfessionalJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "document_type_id", nullable = false, updatable = false)
    private UUID documentTypeId;

    @Column(name = "document_number", nullable = false, length = 30)
    private String documentNumber;

    @Column(name = "first_name", nullable = false, length = 60)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 60)
    private String lastName;

    @Column(name = "professional_type_id", nullable = false, updatable = false)
    private UUID professionalTypeId;

    @Column(name = "license_number", length = 100)
    private String licenseNumber;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "city_id", updatable = false)
    private UUID cityId;

    @Column(name = "contact_id", updatable = false)
    private UUID contactId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", updatable = false)
    private UUID createdBy;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private UUID updatedBy;

    protected ProfessionalJpaEntity() {
        // Required by JPA.
    }

    public ProfessionalJpaEntity(
            UUID id,
            UUID documentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            UUID professionalTypeId,
            String licenseNumber,
            boolean active,
            UUID cityId,
            UUID contactId
    ) {
        this.id = id;
        this.documentTypeId = documentTypeId;
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.professionalTypeId = professionalTypeId;
        this.licenseNumber = licenseNumber;
        this.active = active;
        this.cityId = cityId;
        this.contactId = contactId;
    }

    public void synchronize(
            String documentNumber,
            String firstName,
            String lastName,
            String licenseNumber,
            boolean active
    ) {
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseNumber = licenseNumber;
        this.active = active;
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

    public UUID getDocumentTypeId() {
        return documentTypeId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UUID getProfessionalTypeId() {
        return professionalTypeId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public boolean isActive() {
        return active;
    }

    public UUID getCityId() {
        return cityId;
    }

    public UUID getContactId() {
        return contactId;
    }
}
