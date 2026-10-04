package com.backintro.infrastructure.medicationroute.adapters.out.persistence;

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

import com.backintro.domain.medicationroute.model.aggregate.MedicationRoute;
import com.backintro.infrastructure.medicationroute.adapters.out.persistence.entity.MedicationRouteJpaEntity;
import com.backintro.infrastructure.medicationroute.adapters.out.persistence.mapper.MedicationRoutePersistenceMapper;
import com.backintro.infrastructure.medicationroute.adapters.out.persistence.repository.MedicationRouteJpaRepository;

@ExtendWith(MockitoExtension.class)
class MedicationRouteRepositoryAdapterTest {

    @Mock
    private MedicationRouteJpaRepository springDataRepository;

    private MedicationRouteRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new MedicationRouteRepositoryAdapter(
                springDataRepository,
                new MedicationRoutePersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        MedicationRoute medicationRoute =
                MedicationRoute.register("Oral", "PO");
        when(springDataRepository.findById(medicationRoute.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(MedicationRouteJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        MedicationRoute saved = adapter.save(medicationRoute);

        assertThat(saved.id()).isEqualTo(medicationRoute.id());
        assertThat(saved.name()).isEqualTo("Oral");
        assertThat(saved.code()).isEqualTo("PO");
        verify(springDataRepository).save(any(MedicationRouteJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        MedicationRoute medicationRoute =
                MedicationRoute.register("Oral", "PO");

        adapter.delete(medicationRoute);

        verify(springDataRepository)
                .deleteById(medicationRoute.id().value());
    }
}
