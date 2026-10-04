package com.backintro.infrastructure.clinicalrecord.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import com.backintro.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.backintro.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.infrastructure.clinicalrecord.adapters.out.persistence.entity.ClinicalRecordJpaEntity;

@Component
public class ClinicalRecordPersistenceMapper {

    public ClinicalRecord toDomain(ClinicalRecordJpaEntity entity) {
        return ClinicalRecord.restore(
                new ClinicalRecordId(entity.getId()),
                new PatientId(entity.getPatientId()),
                entity.getCreationDate(),
                entity.getRecordNumber(),
                entity.getOpenedAt(),
                entity.getClosedAt(),
                new ClinicalRecordStatusId(entity.getStatusId())
        );
    }

    public ClinicalRecordJpaEntity toNewEntity(ClinicalRecord clinicalRecord) {
        return new ClinicalRecordJpaEntity(
                clinicalRecord.id().value(),
                clinicalRecord.patientId().value(),
                clinicalRecord.creationDate(),
                clinicalRecord.recordNumber(),
                clinicalRecord.openedAt(),
                clinicalRecord.closedAt(),
                clinicalRecord.statusId().value()
        );
    }

    public void synchronize(
            ClinicalRecord clinicalRecord,
            ClinicalRecordJpaEntity entity
    ) {
        entity.synchronize(
                clinicalRecord.recordNumber(),
                clinicalRecord.closedAt(),
                clinicalRecord.statusId().value()
        );
    }
}
