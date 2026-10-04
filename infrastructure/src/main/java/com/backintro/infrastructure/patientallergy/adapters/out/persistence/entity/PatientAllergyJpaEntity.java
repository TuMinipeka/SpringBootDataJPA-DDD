package com.backintro.infrastructure.patientallergy.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient_allergies")
public class PatientAllergyJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "patient_id", nullable = false, updatable = false)
    private UUID patientId;

    @Column(name = "substance", nullable = false, length = 200)
    private String substance;

    @Column(name = "reaction", columnDefinition = "TEXT")
    private String reaction;

    @Column(name = "severity", length = 20)
    private String severity;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "recorded_at", nullable = false, updatable = false)
    private LocalDateTime recordedAt;

    @Column(name = "recorded_by", updatable = false)
    private UUID recordedBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected PatientAllergyJpaEntity() {
        // Required by JPA.
    }

    public PatientAllergyJpaEntity(
            UUID id,
            UUID patientId,
            String substance,
            String reaction,
            String severity,
            boolean active,
            UUID recordedBy
    ) {
        this.id = id;
        this.patientId = patientId;
        this.substance = substance;
        this.reaction = reaction;
        this.severity = severity;
        this.active = active;
        this.recordedBy = recordedBy;
    }

    public void synchronize(
            String substance,
            String reaction,
            String severity,
            boolean active
    ) {
        this.substance = substance;
        this.reaction = reaction;
        this.severity = severity;
        this.active = active;
    }

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (recordedAt == null) {
            recordedAt = now;
        }
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

    public UUID getPatientId() {
        return patientId;
    }

    public String getSubstance() {
        return substance;
    }

    public String getReaction() {
        return reaction;
    }

    public String getSeverity() {
        return severity;
    }

    public boolean isActive() {
        return active;
    }

    public UUID getRecordedBy() {
        return recordedBy;
    }
}
