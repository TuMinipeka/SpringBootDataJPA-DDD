package com.backintro.infrastructure.encounterstatus.adapters.out.persistence;

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

import com.backintro.domain.encounterstatus.model.aggregate.EncounterStatus;
import com.backintro.infrastructure.encounterstatus.adapters.out.persistence.entity.EncounterStatusJpaEntity;
import com.backintro.infrastructure.encounterstatus.adapters.out.persistence.mapper.EncounterStatusPersistenceMapper;
import com.backintro.infrastructure.encounterstatus.adapters.out.persistence.repository.EncounterStatusJpaRepository;

@ExtendWith(MockitoExtension.class)
class EncounterStatusRepositoryAdapterTest {

    @Mock
    private EncounterStatusJpaRepository springDataRepository;

    private EncounterStatusRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new EncounterStatusRepositoryAdapter(
                springDataRepository,
                new EncounterStatusPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        EncounterStatus encounterStatus = EncounterStatus.register(
                "Scheduled",
                "SCHEDULED"
        );
        when(springDataRepository.findById(encounterStatus.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(EncounterStatusJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EncounterStatus saved = adapter.save(encounterStatus);

        assertThat(saved.id()).isEqualTo(encounterStatus.id());
        assertThat(saved.name()).isEqualTo("Scheduled");
        assertThat(saved.code()).isEqualTo("SCHEDULED");
        assertThat(saved.active()).isTrue();
        verify(springDataRepository).save(any(EncounterStatusJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        EncounterStatus encounterStatus = EncounterStatus.register(
                "Scheduled",
                "SCHEDULED"
        );

        adapter.delete(encounterStatus);

        verify(springDataRepository)
                .deleteById(encounterStatus.id().value());
    }
}
