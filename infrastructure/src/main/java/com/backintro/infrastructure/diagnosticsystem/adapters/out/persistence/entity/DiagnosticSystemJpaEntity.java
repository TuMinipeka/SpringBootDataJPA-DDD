package com.backintro.infrastructure.diagnosticsystem.adapters.out.persistence.entity;

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
        name = "diagnostic_systems",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_diagnostic_systems_code",
                        columnNames = {"code"}
                ),
                @UniqueConstraint(
                        name = "uk_diagnostic_systems_name_version",
                        columnNames = {"name", "version"}
                )
        }
)
public class DiagnosticSystemJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "code", nullable = false, length = 20)
    private String code;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "version", length = 20)
    private String version;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected DiagnosticSystemJpaEntity() {
        // Required by JPA.
    }

    public DiagnosticSystemJpaEntity(
            UUID id,
            String name,
            String code,
            boolean active,
            String version
    ) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.active = active;
        this.version = version;
    }

    public void synchronize(
            String name,
            String code,
            boolean active,
            String version
    ) {
        this.name = name;
        this.code = code;
        this.active = active;
        this.version = version;
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

    public String getVersion() {
        return version;
    }
}
