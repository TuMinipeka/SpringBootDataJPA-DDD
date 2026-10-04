package com.backintro.infrastructure.encountertype.adapters.out.persistence;

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

import com.backintro.domain.encountertype.model.aggregate.EncounterType;
import com.backintro.infrastructure.encountertype.adapters.out.persistence.entity.EncounterTypeJpaEntity;
import com.backintro.infrastructure.encountertype.adapters.out.persistence.mapper.EncounterTypePersistenceMapper;
import com.backintro.infrastructure.encountertype.adapters.out.persistence.repository.EncounterTypeJpaRepository;

@ExtendWith(MockitoExtension.class)
class EncounterTypeRepositoryAdapterTest {

    @Mock
    private EncounterTypeJpaRepository springDataRepository;

    private EncounterTypeRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new EncounterTypeRepositoryAdapter(
                springDataRepository,
                new EncounterTypePersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        EncounterType encounterType = EncounterType.register(
                "Initial Consultation",
                "INITIAL"
        );
        when(springDataRepository.findById(encounterType.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(EncounterTypeJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EncounterType saved = adapter.save(encounterType);

        assertThat(saved.id()).isEqualTo(encounterType.id());
        assertThat(saved.name()).isEqualTo("Initial Consultation");
        assertThat(saved.code()).isEqualTo("INITIAL");
        assertThat(saved.active()).isTrue();
        verify(springDataRepository).save(any(EncounterTypeJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        EncounterType encounterType = EncounterType.register(
                "Initial Consultation",
                "INITIAL"
        );

        adapter.delete(encounterType);

        verify(springDataRepository)
                .deleteById(encounterType.id().value());
    }
}
