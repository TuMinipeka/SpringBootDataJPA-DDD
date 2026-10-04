package com.backintro.infrastructure.clinicalrecord.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "clinical_records")
public class ClinicalRecordJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "patient_id", nullable = false, updatable = false)
    private UUID patientId;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "record_number", nullable = false, unique = true, length = 50)
    private String recordNumber;

    @Column(name = "opened_at", nullable = false, updatable = false)
    private LocalDateTime openedAt;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    @Column(name = "status_id", nullable = false)
    private UUID statusId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", updatable = false)
    private UUID createdBy;

    protected ClinicalRecordJpaEntity() {
        // Required by JPA.
    }

    public ClinicalRecordJpaEntity(
            UUID id,
            UUID patientId,
            LocalDateTime creationDate,
            String recordNumber,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            UUID statusId
    ) {
        this.id = id;
        this.patientId = patientId;
        this.creationDate = creationDate;
        this.recordNumber = recordNumber;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
        this.statusId = statusId;
    }

    public void synchronize(
            String recordNumber,
            LocalDateTime closedAt,
            UUID statusId
    ) {
        this.recordNumber = recordNumber;
        this.closedAt = closedAt;
        this.statusId = statusId;
    }

    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public LocalDateTime getOpenedAt() {
        return openedAt;
    }

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    public UUID getStatusId() {
        return statusId;
    }
}
