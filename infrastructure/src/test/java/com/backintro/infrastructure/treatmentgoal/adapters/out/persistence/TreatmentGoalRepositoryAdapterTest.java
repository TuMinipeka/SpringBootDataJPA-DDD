package com.backintro.infrastructure.treatmentgoal.adapters.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backintro.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import com.backintro.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.backintro.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.backintro.infrastructure.treatmentgoal.adapters.out.persistence.entity.TreatmentGoalJpaEntity;
import com.backintro.infrastructure.treatmentgoal.adapters.out.persistence.mapper.TreatmentGoalPersistenceMapper;
import com.backintro.infrastructure.treatmentgoal.adapters.out.persistence.repository.TreatmentGoalJpaRepository;

@ExtendWith(MockitoExtension.class)
class TreatmentGoalRepositoryAdapterTest {

    @Mock
    private TreatmentGoalJpaRepository springDataRepository;

    private TreatmentGoalRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new TreatmentGoalRepositoryAdapter(
                springDataRepository,
                new TreatmentGoalPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        TreatmentGoal treatmentGoal = newTreatmentGoal();
        when(springDataRepository.findById(treatmentGoal.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(TreatmentGoalJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        TreatmentGoal saved = adapter.save(treatmentGoal);

        assertThat(saved.id()).isEqualTo(treatmentGoal.id());
        assertThat(saved.treatmentPlanId())
                .isEqualTo(treatmentGoal.treatmentPlanId());
        assertThat(saved.description()).isEqualTo("Reduce anxiety symptoms");
        assertThat(saved.targetDate()).isEqualTo(LocalDate.of(2026, 12, 31));
        assertThat(saved.notes()).isEqualTo("Review progress monthly");
        assertThat(saved.statusId()).isEqualTo(treatmentGoal.statusId());
        verify(springDataRepository).save(any(TreatmentGoalJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        TreatmentGoal treatmentGoal = newTreatmentGoal();

        adapter.delete(treatmentGoal);

        verify(springDataRepository).deleteById(treatmentGoal.id().value());
    }

    private TreatmentGoal newTreatmentGoal() {
        return TreatmentGoal.register(
                new TreatmentPlanId(UUID.randomUUID()),
                "Reduce anxiety symptoms",
                LocalDate.of(2026, 12, 31),
                "Review progress monthly",
                new TreatmentGoalStatusId(UUID.randomUUID())
        );
    }
}
