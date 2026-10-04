package com.backintro.infrastructure.treatmentgoalstatus.adapters.out.persistence.entity;

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
        name = "treatment_goal_statuses",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_treatment_goal_statuses_code",
                        columnNames = {"code"}
                ),
                @UniqueConstraint(
                        name = "uk_treatment_goal_statuses_name",
                        columnNames = {"name"}
                )
        }
)
public class TreatmentGoalStatusJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "code", nullable = false, length = 20)
    private String code;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected TreatmentGoalStatusJpaEntity() {
        // Required by JPA.
    }

    public TreatmentGoalStatusJpaEntity(
            UUID id,
            String name,
            String code,
            boolean active
    ) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.active = active;
    }

    public void synchronize(
            String name,
            String code,
            boolean active
    ) {
        this.name = name;
        this.code = code;
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

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public boolean isActive() {
        return active;
    }
}
