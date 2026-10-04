package com.backintro.domain.clinicalrecord.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.clinicalrecord.event.ClinicalRecordRegisteredEvent;
import com.backintro.domain.clinicalrecord.event.ClinicalRecordUpdatedEvent;
import com.backintro.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.backintro.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.patient.model.valueobject.PatientId;

public class ClinicalRecord extends AggregateRoot {

    private final ClinicalRecordId id;
    private final PatientId patientId;
    private final LocalDateTime creationDate;
    private final LocalDateTime openedAt;
    private String recordNumber;
    private LocalDateTime closedAt;
    private ClinicalRecordStatusId statusId;

    private ClinicalRecord(
            ClinicalRecordId id,
            PatientId patientId,
            LocalDateTime creationDate,
            String recordNumber,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            ClinicalRecordStatusId statusId
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.patientId = Objects.requireNonNull(
                patientId,
                "patientId must not be null"
        );
        this.creationDate = Objects.requireNonNull(
                creationDate,
                "creationDate must not be null"
        );
        this.recordNumber = Objects.requireNonNull(
                recordNumber,
                "recordNumber must not be null"
        );
        this.openedAt = Objects.requireNonNull(
                openedAt,
                "openedAt must not be null"
        );
        this.closedAt = closedAt;
        this.statusId = Objects.requireNonNull(
                statusId,
                "statusId must not be null"
        );
    }

    public static ClinicalRecord register(
            PatientId patientId,
            String recordNumber,
            ClinicalRecordStatusId statusId
    ) {
        ClinicalRecordId id = ClinicalRecordId.generate();
        LocalDateTime now = LocalDateTime.now();
        ClinicalRecord clinicalRecord = new ClinicalRecord(
                id,
                patientId,
                now,
                recordNumber,
                now,
                null,
                statusId
        );

        clinicalRecord.recordEvent(
                new ClinicalRecordRegisteredEvent(
                        id,
                        now
                )
        );

        return clinicalRecord;
    }

    public static ClinicalRecord restore(
            ClinicalRecordId id,
            PatientId patientId,
            LocalDateTime creationDate,
            String recordNumber,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            ClinicalRecordStatusId statusId
    ) {
        return new ClinicalRecord(
                id,
                patientId,
                creationDate,
                recordNumber,
                openedAt,
                closedAt,
                statusId
        );
    }

    public void update(
            String recordNumber,
            LocalDateTime closedAt,
            ClinicalRecordStatusId statusId
    ) {
        this.recordNumber = Objects.requireNonNull(
                recordNumber,
                "recordNumber must not be null"
        );
        this.closedAt = closedAt;
        this.statusId = Objects.requireNonNull(
                statusId,
                "statusId must not be null"
        );

        recordEvent(
                new ClinicalRecordUpdatedEvent(
                        this.id,
                        this.recordNumber,
                        this.closedAt,
                        this.statusId,
                        LocalDateTime.now()
                )
        );
    }

    public ClinicalRecordId id() {
        return id;
    }

    public PatientId patientId() {
        return patientId;
    }

    public LocalDateTime creationDate() {
        return creationDate;
    }

    public String recordNumber() {
        return recordNumber;
    }

    public LocalDateTime openedAt() {
        return openedAt;
    }

    public LocalDateTime closedAt() {
        return closedAt;
    }

    public ClinicalRecordStatusId statusId() {
        return statusId;
    }
}
