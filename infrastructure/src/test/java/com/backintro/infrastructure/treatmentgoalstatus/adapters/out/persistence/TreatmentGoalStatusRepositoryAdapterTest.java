package com.backintro.infrastructure.treatmentgoalstatus.adapters.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backintro.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import com.backintro.infrastructure.treatmentgoalstatus.adapters.out.persistence.entity.TreatmentGoalStatusJpaEntity;
import com.backintro.infrastructure.treatmentgoalstatus.adapters.out.persistence.mapper.TreatmentGoalStatusPersistenceMapper;
import com.backintro.infrastructure.treatmentgoalstatus.adapters.out.persistence.repository.TreatmentGoalStatusJpaRepository;

@ExtendWith(MockitoExtension.class)
class TreatmentGoalStatusRepositoryAdapterTest {

    @Mock
    private TreatmentGoalStatusJpaRepository springDataRepository;

    private TreatmentGoalStatusRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new TreatmentGoalStatusRepositoryAdapter(
                springDataRepository,
                new TreatmentGoalStatusPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        TreatmentGoalStatus treatmentGoalStatus =
                TreatmentGoalStatus.register(
                        "In progress",
                        "IN_PROGRESS"
                );
        when(springDataRepository.findById(treatmentGoalStatus.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(
                any(TreatmentGoalStatusJpaEntity.class)
        )).thenAnswer(invocation -> invocation.getArgument(0));

        TreatmentGoalStatus saved = adapter.save(treatmentGoalStatus);

        assertThat(saved.id()).isEqualTo(treatmentGoalStatus.id());
        assertThat(saved.name()).isEqualTo("In progress");
        assertThat(saved.code()).isEqualTo("IN_PROGRESS");
        assertThat(saved.active()).isTrue();
        verify(springDataRepository).save(
                any(TreatmentGoalStatusJpaEntity.class)
        );
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        TreatmentGoalStatus treatmentGoalStatus =
                TreatmentGoalStatus.register(
                        "In progress",
                        "IN_PROGRESS"
                );

        adapter.delete(treatmentGoalStatus);

        verify(springDataRepository)
                .deleteById(treatmentGoalStatus.id().value());
    }
}
