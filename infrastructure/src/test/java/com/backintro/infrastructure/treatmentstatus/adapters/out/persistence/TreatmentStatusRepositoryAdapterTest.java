package com.backintro.infrastructure.treatmentstatus.adapters.out.persistence;

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

import com.backintro.domain.treatmentstatus.model.aggregate.TreatmentStatus;
import com.backintro.infrastructure.treatmentstatus.adapters.out.persistence.entity.TreatmentStatusJpaEntity;
import com.backintro.infrastructure.treatmentstatus.adapters.out.persistence.mapper.TreatmentStatusPersistenceMapper;
import com.backintro.infrastructure.treatmentstatus.adapters.out.persistence.repository.TreatmentStatusJpaRepository;

@ExtendWith(MockitoExtension.class)
class TreatmentStatusRepositoryAdapterTest {

    @Mock
    private TreatmentStatusJpaRepository springDataRepository;

    private TreatmentStatusRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new TreatmentStatusRepositoryAdapter(
                springDataRepository,
                new TreatmentStatusPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        TreatmentStatus treatmentStatus = TreatmentStatus.register(
                "Active",
                "ACTIVE"
        );
        when(springDataRepository.findById(treatmentStatus.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(TreatmentStatusJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        TreatmentStatus saved = adapter.save(treatmentStatus);

        assertThat(saved.id()).isEqualTo(treatmentStatus.id());
        assertThat(saved.name()).isEqualTo("Active");
        assertThat(saved.code()).isEqualTo("ACTIVE");
        assertThat(saved.active()).isTrue();
        verify(springDataRepository).save(any(TreatmentStatusJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        TreatmentStatus treatmentStatus = TreatmentStatus.register(
                "Active",
                "ACTIVE"
        );

        adapter.delete(treatmentStatus);

        verify(springDataRepository)
                .deleteById(treatmentStatus.id().value());
    }
}
