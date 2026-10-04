package com.backintro.infrastructure.treatmentgoal.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import com.backintro.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import com.backintro.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.backintro.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.backintro.infrastructure.treatmentgoal.adapters.out.persistence.entity.TreatmentGoalJpaEntity;

@Component
public class TreatmentGoalPersistenceMapper {

    public TreatmentGoal toDomain(TreatmentGoalJpaEntity entity) {
        return TreatmentGoal.restore(
                new TreatmentGoalId(entity.getId()),
                new TreatmentPlanId(entity.getTreatmentPlanId()),
                entity.getDescription(),
                entity.getTargetDate(),
                entity.getCompletedAt(),
                entity.getNotes(),
                new TreatmentGoalStatusId(entity.getStatusId())
        );
    }

    public TreatmentGoalJpaEntity toNewEntity(TreatmentGoal treatmentGoal) {
        return new TreatmentGoalJpaEntity(
                treatmentGoal.id().value(),
                treatmentGoal.treatmentPlanId().value(),
                treatmentGoal.description(),
                treatmentGoal.targetDate(),
                treatmentGoal.completedAt(),
                treatmentGoal.notes(),
                treatmentGoal.statusId().value()
        );
    }

    public void synchronize(
            TreatmentGoal treatmentGoal,
            TreatmentGoalJpaEntity entity
    ) {
        entity.synchronize(
                treatmentGoal.description(),
                treatmentGoal.targetDate(),
                treatmentGoal.completedAt(),
                treatmentGoal.notes(),
                treatmentGoal.statusId().value()
        );
    }
}
