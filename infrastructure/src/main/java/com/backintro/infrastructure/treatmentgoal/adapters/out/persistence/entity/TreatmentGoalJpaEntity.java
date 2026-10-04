package com.backintro.infrastructure.treatmentgoal.adapters.out.persistence.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "treatment_goals")
public class TreatmentGoalJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "treatment_plan_id", nullable = false, updatable = false)
    private UUID treatmentPlanId;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "target_date")
    private LocalDate targetDate;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "treatment_goal_id", nullable = false)
    private UUID statusId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected TreatmentGoalJpaEntity() {
        // Required by JPA.
    }

    public TreatmentGoalJpaEntity(
            UUID id,
            UUID treatmentPlanId,
            String description,
            LocalDate targetDate,
            LocalDateTime completedAt,
            String notes,
            UUID statusId
    ) {
        this.id = id;
        this.treatmentPlanId = treatmentPlanId;
        this.description = description;
        this.targetDate = targetDate;
        this.completedAt = completedAt;
        this.notes = notes;
        this.statusId = statusId;
    }

    public void synchronize(
            String description,
            LocalDate targetDate,
            LocalDateTime completedAt,
            String notes,
            UUID statusId
    ) {
        this.description = description;
        this.targetDate = targetDate;
        this.completedAt = completedAt;
        this.notes = notes;
        this.statusId = statusId;
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

    public UUID getTreatmentPlanId() {
        return treatmentPlanId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public String getNotes() {
        return notes;
    }

    public UUID getStatusId() {
        return statusId;
    }
}
