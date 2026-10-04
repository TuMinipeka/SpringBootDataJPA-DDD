package com.backintro.infrastructure.contact.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "contacts")
public class ContactJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "full_name", nullable = false, length = 200)
    private String fullName;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "city_id")
    private UUID cityId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", updatable = false)
    private UUID createdBy;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private UUID updatedBy;

    protected ContactJpaEntity() {
        // Required by JPA.
    }

    public ContactJpaEntity(
            UUID id,
            String fullName,
            String email,
            String notes,
            UUID cityId
    ) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.notes = notes;
        this.cityId = cityId;
    }

    public void synchronize(
            String fullName,
            String email,
            String notes,
            UUID cityId
    ) {
        this.fullName = fullName;
        this.email = email;
        this.notes = notes;
        this.cityId = cityId;
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

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getNotes() {
        return notes;
    }

    public UUID getCityId() {
        return cityId;
    }
}
