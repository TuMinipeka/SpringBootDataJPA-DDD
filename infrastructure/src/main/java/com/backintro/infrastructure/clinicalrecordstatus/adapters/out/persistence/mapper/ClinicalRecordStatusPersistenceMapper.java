package com.backintro.infrastructure.clinicalrecordstatus.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import com.backintro.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.backintro.infrastructure.clinicalrecordstatus.adapters.out.persistence.entity.ClinicalRecordStatusJpaEntity;

@Component
public class ClinicalRecordStatusPersistenceMapper {

    public ClinicalRecordStatus toDomain(
            ClinicalRecordStatusJpaEntity entity
    ) {
        return ClinicalRecordStatus.restore(
                new ClinicalRecordStatusId(entity.getId()),
                entity.getName(),
                entity.getCode()
        );
    }

    public ClinicalRecordStatusJpaEntity toNewEntity(
            ClinicalRecordStatus clinicalRecordStatus
    ) {
        return new ClinicalRecordStatusJpaEntity(
                clinicalRecordStatus.id().value(),
                clinicalRecordStatus.name(),
                clinicalRecordStatus.code()
        );
    }

    public void synchronize(
            ClinicalRecordStatus clinicalRecordStatus,
            ClinicalRecordStatusJpaEntity entity
    ) {
        entity.synchronize(
                clinicalRecordStatus.name(),
                clinicalRecordStatus.code()
        );
    }
}
