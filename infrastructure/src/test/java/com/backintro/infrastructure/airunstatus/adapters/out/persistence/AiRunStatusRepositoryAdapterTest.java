package com.backintro.infrastructure.airunstatus.adapters.out.persistence;

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

import com.backintro.domain.airunstatus.model.aggregate.AiRunStatus;
import com.backintro.infrastructure.airunstatus.adapters.out.persistence.entity.AiRunStatusJpaEntity;
import com.backintro.infrastructure.airunstatus.adapters.out.persistence.mapper.AiRunStatusPersistenceMapper;
import com.backintro.infrastructure.airunstatus.adapters.out.persistence.repository.AiRunStatusJpaRepository;

@ExtendWith(MockitoExtension.class)
class AiRunStatusRepositoryAdapterTest {

    @Mock
    private AiRunStatusJpaRepository springDataRepository;

    private AiRunStatusRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new AiRunStatusRepositoryAdapter(
                springDataRepository,
                new AiRunStatusPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        AiRunStatus aiRunStatus =
                AiRunStatus.register("Pending");
        when(springDataRepository.findById(aiRunStatus.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(AiRunStatusJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        AiRunStatus saved = adapter.save(aiRunStatus);

        assertThat(saved.id()).isEqualTo(aiRunStatus.id());
        assertThat(saved.nameStatus()).isEqualTo("Pending");
        verify(springDataRepository).save(any(AiRunStatusJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        AiRunStatus aiRunStatus =
                AiRunStatus.register("Pending");

        adapter.delete(aiRunStatus);

        verify(springDataRepository)
                .deleteById(aiRunStatus.id().value());
    }
}
