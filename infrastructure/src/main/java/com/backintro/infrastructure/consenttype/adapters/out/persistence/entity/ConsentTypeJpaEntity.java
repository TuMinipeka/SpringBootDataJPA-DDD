package com.backintro.infrastructure.consenttype.adapters.out.persistence.entity;

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
        name = "consent_types",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_consent_types_code",
                        columnNames = {"code"}
                ),
                @UniqueConstraint(
                        name = "uk_consent_types_name",
                        columnNames = {"name"}
                )
        }
)
public class ConsentTypeJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "code", nullable = false, length = 20)
    private String code;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected ConsentTypeJpaEntity() {
        // Required by JPA.
    }

    public ConsentTypeJpaEntity(
            UUID id,
            String name,
            String code,
            boolean active,
            String description
    ) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.active = active;
        this.description = description;
    }

    public void synchronize(
            String name,
            String code,
            boolean active,
            String description
    ) {
        this.name = name;
        this.code = code;
        this.active = active;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public boolean isActive() {
        return active;
    }

    public String getDescription() {
        return description;
    }
}
