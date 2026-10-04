package com.backintro.infrastructure.encounter.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.backintro.domain.encounter.model.aggregate.Encounter;
import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.encountermodality.model.valueobject.EncounterModalityId;
import com.backintro.domain.encounterstatus.model.valueobject.EncounterStatusId;
import com.backintro.domain.encountertype.model.valueobject.EncounterTypeId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.infrastructure.encounter.adapters.out.persistence.entity.EncounterJpaEntity;

@Component
public class EncounterPersistenceMapper {

    public Encounter toDomain(EncounterJpaEntity entity) {
        return Encounter.restore(
                new EncounterId(entity.getId()),
                new ClinicalRecordId(entity.getClinicalRecordId()),
                new ProfessionalId(entity.getProfessionalId()),
                new EncounterTypeId(entity.getEncounterTypeId()),
                entity.getStartedAt(),
                entity.getEndedAt(),
                entity.getReasonForVisit(),
                entity.getCurrentCondition(),
                new EncounterModalityId(entity.getModalityId()),
                new EncounterStatusId(entity.getStatusId())
        );
    }

    public EncounterJpaEntity toNewEntity(Encounter encounter) {
        return new EncounterJpaEntity(
                encounter.id().value(),
                encounter.clinicalRecordId().value(),
                encounter.professionalId().value(),
                encounter.encounterTypeId().value(),
                encounter.startedAt(),
                encounter.endedAt(),
                encounter.reasonForVisit(),
                encounter.currentCondition(),
                encounter.modalityId().value(),
                encounter.statusId().value()
        );
    }

    public void synchronize(
            Encounter encounter,
            EncounterJpaEntity entity
    ) {
        entity.synchronize(
                encounter.endedAt(),
                encounter.reasonForVisit(),
                encounter.currentCondition(),
                encounter.statusId().value()
        );
    }
}
