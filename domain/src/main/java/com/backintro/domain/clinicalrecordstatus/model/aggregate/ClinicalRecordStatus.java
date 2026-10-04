package com.backintro.domain.clinicalrecordstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.clinicalrecordstatus.event.ClinicalRecordStatusRegisteredEvent;
import com.backintro.domain.clinicalrecordstatus.event.ClinicalRecordStatusUpdatedEvent;
import com.backintro.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.backintro.domain.common.model.AggregateRoot;

public class ClinicalRecordStatus extends AggregateRoot {

    private final ClinicalRecordStatusId id;
    private String name;
    private String code;

    private ClinicalRecordStatus(
            ClinicalRecordStatusId id,
            String name,
            String code
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
    }

    public static ClinicalRecordStatus register(
            String name,
            String code
    ) {
        ClinicalRecordStatusId id = ClinicalRecordStatusId.generate();
        ClinicalRecordStatus clinicalRecordStatus = new ClinicalRecordStatus(
                id,
                name,
                code
        );

        clinicalRecordStatus.recordEvent(
                new ClinicalRecordStatusRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return clinicalRecordStatus;
    }

    public static ClinicalRecordStatus restore(
            ClinicalRecordStatusId id,
            String name,
            String code
    ) {
        return new ClinicalRecordStatus(id, name, code);
    }

    public void update(
            String name,
            String code
    ) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");

        recordEvent(
                new ClinicalRecordStatusUpdatedEvent(
                        this.id,
                        this.name,
                        this.code,
                        LocalDateTime.now()
                )
        );
    }

    public ClinicalRecordStatusId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String code() {
        return code;
    }
}
