package com.backintro.infrastructure.escalationstatus.adapters.out.persistence;

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

import com.backintro.domain.escalationstatus.model.aggregate.EscalationStatus;
import com.backintro.infrastructure.escalationstatus.adapters.out.persistence.entity.EscalationStatusJpaEntity;
import com.backintro.infrastructure.escalationstatus.adapters.out.persistence.mapper.EscalationStatusPersistenceMapper;
import com.backintro.infrastructure.escalationstatus.adapters.out.persistence.repository.EscalationStatusJpaRepository;

@ExtendWith(MockitoExtension.class)
class EscalationStatusRepositoryAdapterTest {

    @Mock
    private EscalationStatusJpaRepository springDataRepository;

    private EscalationStatusRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new EscalationStatusRepositoryAdapter(
                springDataRepository,
                new EscalationStatusPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        EscalationStatus escalationStatus =
                EscalationStatus.register("Open");
        when(springDataRepository.findById(escalationStatus.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(EscalationStatusJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EscalationStatus saved = adapter.save(escalationStatus);

        assertThat(saved.id()).isEqualTo(escalationStatus.id());
        assertThat(saved.nameStatus()).isEqualTo("Open");
        verify(springDataRepository).save(any(EscalationStatusJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        EscalationStatus escalationStatus =
                EscalationStatus.register("Open");

        adapter.delete(escalationStatus);

        verify(springDataRepository)
                .deleteById(escalationStatus.id().value());
    }
}
