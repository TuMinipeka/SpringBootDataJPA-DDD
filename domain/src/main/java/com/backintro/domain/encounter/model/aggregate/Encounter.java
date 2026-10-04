package com.backintro.domain.encounter.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.encounter.event.EncounterRegisteredEvent;
import com.backintro.domain.encounter.event.EncounterUpdatedEvent;
import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.encountermodality.model.valueobject.EncounterModalityId;
import com.backintro.domain.encounterstatus.model.valueobject.EncounterStatusId;
import com.backintro.domain.encountertype.model.valueobject.EncounterTypeId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

public class Encounter extends AggregateRoot {

    private final EncounterId id;
    private final ClinicalRecordId clinicalRecordId;
    private final ProfessionalId professionalId;
    private final EncounterTypeId encounterTypeId;
    private final LocalDateTime startedAt;
    private final EncounterModalityId modalityId;
    private LocalDateTime endedAt;
    private String reasonForVisit;
    private String currentCondition;
    private EncounterStatusId statusId;

    private Encounter(
            EncounterId id,
            ClinicalRecordId clinicalRecordId,
            ProfessionalId professionalId,
            EncounterTypeId encounterTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String reasonForVisit,
            String currentCondition,
            EncounterModalityId modalityId,
            EncounterStatusId statusId
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.clinicalRecordId = Objects.requireNonNull(
                clinicalRecordId,
                "clinicalRecordId must not be null"
        );
        this.professionalId = Objects.requireNonNull(
                professionalId,
                "professionalId must not be null"
        );
        this.encounterTypeId = Objects.requireNonNull(
                encounterTypeId,
                "encounterTypeId must not be null"
        );
        this.startedAt = Objects.requireNonNull(
                startedAt,
                "startedAt must not be null"
        );
        this.endedAt = endedAt;
        this.reasonForVisit = reasonForVisit;
        this.currentCondition = currentCondition;
        this.modalityId = Objects.requireNonNull(
                modalityId,
                "modalityId must not be null"
        );
        this.statusId = Objects.requireNonNull(
                statusId,
                "statusId must not be null"
        );
    }

    public static Encounter register(
            ClinicalRecordId clinicalRecordId,
            ProfessionalId professionalId,
            EncounterTypeId encounterTypeId,
            LocalDateTime startedAt,
            String reasonForVisit,
            String currentCondition,
            EncounterModalityId modalityId,
            EncounterStatusId statusId
    ) {
        EncounterId id = EncounterId.generate();
        Encounter encounter = new Encounter(
                id,
                clinicalRecordId,
                professionalId,
                encounterTypeId,
                startedAt,
                null,
                reasonForVisit,
                currentCondition,
                modalityId,
                statusId
        );

        encounter.recordEvent(
                new EncounterRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return encounter;
    }

    public static Encounter restore(
            EncounterId id,
            ClinicalRecordId clinicalRecordId,
            ProfessionalId professionalId,
            EncounterTypeId encounterTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String reasonForVisit,
            String currentCondition,
            EncounterModalityId modalityId,
            EncounterStatusId statusId
    ) {
        return new Encounter(
                id,
                clinicalRecordId,
                professionalId,
                encounterTypeId,
                startedAt,
                endedAt,
                reasonForVisit,
                currentCondition,
                modalityId,
                statusId
        );
    }

    public void update(
            LocalDateTime endedAt,
            String reasonForVisit,
            String currentCondition,
            EncounterStatusId statusId
    ) {
        this.endedAt = endedAt;
        this.reasonForVisit = reasonForVisit;
        this.currentCondition = currentCondition;
        this.statusId = Objects.requireNonNull(
                statusId,
                "statusId must not be null"
        );

        recordEvent(
                new EncounterUpdatedEvent(
                        this.id,
                        this.endedAt,
                        this.reasonForVisit,
                        this.currentCondition,
                        this.statusId,
                        LocalDateTime.now()
                )
        );
    }

    public EncounterId id() {
        return id;
    }

    public ClinicalRecordId clinicalRecordId() {
        return clinicalRecordId;
    }

    public ProfessionalId professionalId() {
        return professionalId;
    }

    public EncounterTypeId encounterTypeId() {
        return encounterTypeId;
    }

    public LocalDateTime startedAt() {
        return startedAt;
    }

    public LocalDateTime endedAt() {
        return endedAt;
    }

    public String reasonForVisit() {
        return reasonForVisit;
    }

    public String currentCondition() {
        return currentCondition;
    }

    public EncounterModalityId modalityId() {
        return modalityId;
    }

    public EncounterStatusId statusId() {
        return statusId;
    }
}
