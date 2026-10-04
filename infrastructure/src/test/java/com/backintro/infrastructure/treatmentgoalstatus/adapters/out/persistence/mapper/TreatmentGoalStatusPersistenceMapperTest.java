package com.backintro.infrastructure.treatmentgoalstatus.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;

class TreatmentGoalStatusPersistenceMapperTest {

    private final TreatmentGoalStatusPersistenceMapper mapper =
            new TreatmentGoalStatusPersistenceMapper();

    @Test
    void mapsTreatmentGoalStatusInBothDirectionsWithoutDomainEvents() {
        TreatmentGoalStatus original = TreatmentGoalStatus.register(
                "In progress",
                "IN_PROGRESS"
        );

        TreatmentGoalStatus restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.name()).isEqualTo("In progress");
        assertThat(restored.code()).isEqualTo("IN_PROGRESS");
        assertThat(restored.active()).isTrue();
        assertThat(restored.domainEvents()).isEmpty();
    }
}
