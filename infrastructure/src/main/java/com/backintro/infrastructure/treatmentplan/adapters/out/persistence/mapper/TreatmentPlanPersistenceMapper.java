package com.backintro.infrastructure.treatmentplan.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.treatmentplan.model.aggregate.TreatmentPlan;
import com.backintro.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.backintro.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import com.backintro.infrastructure.treatmentplan.adapters.out.persistence.entity.TreatmentPlanJpaEntity;

@Component
public class TreatmentPlanPersistenceMapper {

    public TreatmentPlan toDomain(TreatmentPlanJpaEntity entity) {
        return TreatmentPlan.restore(
                new TreatmentPlanId(entity.getId()),
                new EncounterId(entity.getEncounterId()),
                new ProfessionalId(entity.getProfessionalId()),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStartDate(),
                entity.getEndDate(),
                new TreatmentStatusId(entity.getStatusId())
        );
    }

    public TreatmentPlanJpaEntity toNewEntity(TreatmentPlan treatmentPlan) {
        return new TreatmentPlanJpaEntity(
                treatmentPlan.id().value(),
                treatmentPlan.encounterId().value(),
                treatmentPlan.professionalId().value(),
                treatmentPlan.title(),
                treatmentPlan.description(),
                treatmentPlan.startDate(),
                treatmentPlan.endDate(),
                treatmentPlan.statusId().value()
        );
    }

    public void synchronize(
            TreatmentPlan treatmentPlan,
            TreatmentPlanJpaEntity entity
    ) {
        entity.synchronize(
                treatmentPlan.title(),
                treatmentPlan.description(),
                treatmentPlan.endDate(),
                treatmentPlan.statusId().value()
        );
    }
}
