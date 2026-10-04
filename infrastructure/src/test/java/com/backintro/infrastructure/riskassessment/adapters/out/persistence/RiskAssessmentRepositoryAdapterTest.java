package com.backintro.infrastructure.riskassessment.adapters.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.riskassessment.model.aggregate.RiskAssessment;
import com.backintro.domain.risklevel.model.valueobject.RiskLevelId;
import com.backintro.infrastructure.riskassessment.adapters.out.persistence.entity.RiskAssessmentJpaEntity;
import com.backintro.infrastructure.riskassessment.adapters.out.persistence.mapper.RiskAssessmentPersistenceMapper;
import com.backintro.infrastructure.riskassessment.adapters.out.persistence.repository.RiskAssessmentJpaRepository;

@ExtendWith(MockitoExtension.class)
class RiskAssessmentRepositoryAdapterTest {

    @Mock
    private RiskAssessmentJpaRepository springDataRepository;

    private RiskAssessmentRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new RiskAssessmentRepositoryAdapter(
                springDataRepository,
                new RiskAssessmentPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        RiskAssessment riskAssessment = newRiskAssessment();
        when(springDataRepository.findById(riskAssessment.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(RiskAssessmentJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        RiskAssessment saved = adapter.save(riskAssessment);

        assertThat(saved.id()).isEqualTo(riskAssessment.id());
        assertThat(saved.encounterId()).isEqualTo(riskAssessment.encounterId());
        assertThat(saved.riskLevelId()).isEqualTo(riskAssessment.riskLevelId());
        assertThat(saved.suicidalIdeation()).isTrue();
        assertThat(saved.selfHarm()).isTrue();
        assertThat(saved.riskFactors()).isEqualTo("Previous attempts");
        assertThat(saved.assessedAt()).isEqualTo(riskAssessment.assessedAt());
        assertThat(saved.assessedBy()).isEqualTo(riskAssessment.assessedBy());
        verify(springDataRepository).save(any(RiskAssessmentJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        RiskAssessment riskAssessment = newRiskAssessment();

        adapter.delete(riskAssessment);

        verify(springDataRepository)
                .deleteById(riskAssessment.id().value());
    }

    private RiskAssessment newRiskAssessment() {
        return RiskAssessment.register(
                new EncounterId(UUID.randomUUID()),
                new RiskLevelId(UUID.randomUUID()),
                true,
                false,
                false,
                true,
                false,
                "Previous attempts",
                "Family support",
                "Safety plan",
                "Requires monitoring",
                new ProfessionalId(UUID.randomUUID())
        );
    }
}
