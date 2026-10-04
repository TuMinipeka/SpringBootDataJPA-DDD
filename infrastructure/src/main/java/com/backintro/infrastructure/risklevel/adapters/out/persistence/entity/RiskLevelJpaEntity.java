package com.backintro.infrastructure.risklevel.adapters.out.persistence.entity;

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
        name = "risk_levels",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_risk_levels_code",
                        columnNames = {"code"}
                ),
                @UniqueConstraint(
                        name = "uk_risk_levels_name",
                        columnNames = {"name"}
                )
        }
)
public class RiskLevelJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "code", nullable = false, length = 20)
    private String code;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "severity", nullable = false)
    private int severity;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected RiskLevelJpaEntity() {
        // Required by JPA.
    }

    public RiskLevelJpaEntity(
            UUID id,
            String name,
            String code,
            boolean active,
            int severity
    ) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.active = active;
        this.severity = severity;
    }

    public void synchronize(
            String name,
            String code,
            boolean active,
            int severity
    ) {
        this.name = name;
        this.code = code;
        this.active = active;
        this.severity = severity;
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

    public int getSeverity() {
        return severity;
    }
}
