package com.backintro.infrastructure.treatmentgoal.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.backintro.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import com.backintro.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.backintro.domain.treatmentplan.model.valueobject.TreatmentPlanId;

class TreatmentGoalPersistenceMapperTest {

    private final TreatmentGoalPersistenceMapper mapper =
            new TreatmentGoalPersistenceMapper();

    @Test
    void mapsTreatmentGoalInBothDirectionsWithoutCreatingDomainEvents() {
        TreatmentPlanId treatmentPlanId =
                new TreatmentPlanId(UUID.randomUUID());
        TreatmentGoalStatusId statusId =
                new TreatmentGoalStatusId(UUID.randomUUID());
        TreatmentGoal original = TreatmentGoal.register(
                treatmentPlanId,
                "Reduce anxiety symptoms",
                LocalDate.of(2026, 12, 31),
                "Review progress monthly",
                statusId
        );

        TreatmentGoal restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.treatmentPlanId()).isEqualTo(treatmentPlanId);
        assertThat(restored.description()).isEqualTo("Reduce anxiety symptoms");
        assertThat(restored.targetDate()).isEqualTo(LocalDate.of(2026, 12, 31));
        assertThat(restored.completedAt()).isNull();
        assertThat(restored.notes()).isEqualTo("Review progress monthly");
        assertThat(restored.statusId()).isEqualTo(statusId);
        assertThat(restored.domainEvents()).isEmpty();
    }
}
