package com.backintro.infrastructure.encounter.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "encounters")
public class EncounterJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "clinical_record_id", nullable = false, updatable = false)
    private UUID clinicalRecordId;

    @Column(name = "professional_id", nullable = false, updatable = false)
    private UUID professionalId;

    @Column(name = "encounter_type_id", nullable = false, updatable = false)
    private UUID encounterTypeId;

    @Column(name = "started_at", nullable = false, updatable = false)
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @Column(name = "reason_for_visit", columnDefinition = "TEXT")
    private String reasonForVisit;

    @Column(name = "current_condition", columnDefinition = "TEXT")
    private String currentCondition;

    @Column(name = "modality_id", nullable = false, updatable = false)
    private UUID modalityId;

    @Column(name = "status_id", nullable = false)
    private UUID statusId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", updatable = false)
    private UUID createdBy;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private UUID updatedBy;

    protected EncounterJpaEntity() {
        // Required by JPA.
    }

    public EncounterJpaEntity(
            UUID id,
            UUID clinicalRecordId,
            UUID professionalId,
            UUID encounterTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String reasonForVisit,
            String currentCondition,
            UUID modalityId,
            UUID statusId
    ) {
        this.id = id;
        this.clinicalRecordId = clinicalRecordId;
        this.professionalId = professionalId;
        this.encounterTypeId = encounterTypeId;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.reasonForVisit = reasonForVisit;
        this.currentCondition = currentCondition;
        this.modalityId = modalityId;
        this.statusId = statusId;
    }

    public void synchronize(
            LocalDateTime endedAt,
            String reasonForVisit,
            String currentCondition,
            UUID statusId
    ) {
        this.endedAt = endedAt;
        this.reasonForVisit = reasonForVisit;
        this.currentCondition = currentCondition;
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

    public UUID getClinicalRecordId() {
        return clinicalRecordId;
    }

    public UUID getProfessionalId() {
        return professionalId;
    }

    public UUID getEncounterTypeId() {
        return encounterTypeId;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public String getCurrentCondition() {
        return currentCondition;
    }

    public UUID getModalityId() {
        return modalityId;
    }

    public UUID getStatusId() {
        return statusId;
    }
}
