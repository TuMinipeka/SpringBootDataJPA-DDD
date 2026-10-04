package com.backintro.infrastructure.treatmentplan.adapters.out.persistence;

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

import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.treatmentplan.model.aggregate.TreatmentPlan;
import com.backintro.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import com.backintro.infrastructure.treatmentplan.adapters.out.persistence.entity.TreatmentPlanJpaEntity;
import com.backintro.infrastructure.treatmentplan.adapters.out.persistence.mapper.TreatmentPlanPersistenceMapper;
import com.backintro.infrastructure.treatmentplan.adapters.out.persistence.repository.TreatmentPlanJpaRepository;

@ExtendWith(MockitoExtension.class)
class TreatmentPlanRepositoryAdapterTest {

    @Mock
    private TreatmentPlanJpaRepository springDataRepository;

    private TreatmentPlanRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new TreatmentPlanRepositoryAdapter(
                springDataRepository,
                new TreatmentPlanPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        TreatmentPlan treatmentPlan = newTreatmentPlan();
        when(springDataRepository.findById(treatmentPlan.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(TreatmentPlanJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        TreatmentPlan saved = adapter.save(treatmentPlan);

        assertThat(saved.id()).isEqualTo(treatmentPlan.id());
        assertThat(saved.encounterId()).isEqualTo(treatmentPlan.encounterId());
        assertThat(saved.professionalId())
                .isEqualTo(treatmentPlan.professionalId());
        assertThat(saved.title()).isEqualTo("Cognitive behavioral plan");
        assertThat(saved.description()).isEqualTo("Weekly sessions");
        assertThat(saved.startDate()).isEqualTo(LocalDate.of(2026, 10, 4));
        assertThat(saved.statusId()).isEqualTo(treatmentPlan.statusId());
        verify(springDataRepository).save(any(TreatmentPlanJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        TreatmentPlan treatmentPlan = newTreatmentPlan();

        adapter.delete(treatmentPlan);

        verify(springDataRepository).deleteById(treatmentPlan.id().value());
    }

    private TreatmentPlan newTreatmentPlan() {
        return TreatmentPlan.register(
                new EncounterId(UUID.randomUUID()),
                new ProfessionalId(UUID.randomUUID()),
                "Cognitive behavioral plan",
                "Weekly sessions",
                LocalDate.of(2026, 10, 4),
                new TreatmentStatusId(UUID.randomUUID())
        );
    }
}
