package com.backintro.infrastructure.risklevel.adapters.out.persistence;

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

import com.backintro.domain.risklevel.model.aggregate.RiskLevel;
import com.backintro.infrastructure.risklevel.adapters.out.persistence.entity.RiskLevelJpaEntity;
import com.backintro.infrastructure.risklevel.adapters.out.persistence.mapper.RiskLevelPersistenceMapper;
import com.backintro.infrastructure.risklevel.adapters.out.persistence.repository.RiskLevelJpaRepository;

@ExtendWith(MockitoExtension.class)
class RiskLevelRepositoryAdapterTest {

    @Mock
    private RiskLevelJpaRepository springDataRepository;

    private RiskLevelRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new RiskLevelRepositoryAdapter(
                springDataRepository,
                new RiskLevelPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        RiskLevel riskLevel = RiskLevel.register("High", "HIGH", 3);
        when(springDataRepository.findById(riskLevel.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(RiskLevelJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        RiskLevel saved = adapter.save(riskLevel);

        assertThat(saved.id()).isEqualTo(riskLevel.id());
        assertThat(saved.name()).isEqualTo("High");
        assertThat(saved.code()).isEqualTo("HIGH");
        assertThat(saved.active()).isTrue();
        assertThat(saved.severity()).isEqualTo(3);
        verify(springDataRepository).save(any(RiskLevelJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        RiskLevel riskLevel = RiskLevel.register("High", "HIGH", 3);

        adapter.delete(riskLevel);

        verify(springDataRepository).deleteById(riskLevel.id().value());
    }
}
