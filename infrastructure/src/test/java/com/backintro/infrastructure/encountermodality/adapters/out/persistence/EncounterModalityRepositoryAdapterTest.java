package com.backintro.infrastructure.encountermodality.adapters.out.persistence;

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

import com.backintro.domain.encountermodality.model.aggregate.EncounterModality;
import com.backintro.infrastructure.encountermodality.adapters.out.persistence.entity.EncounterModalityJpaEntity;
import com.backintro.infrastructure.encountermodality.adapters.out.persistence.mapper.EncounterModalityPersistenceMapper;
import com.backintro.infrastructure.encountermodality.adapters.out.persistence.repository.EncounterModalityJpaRepository;

@ExtendWith(MockitoExtension.class)
class EncounterModalityRepositoryAdapterTest {

    @Mock
    private EncounterModalityJpaRepository springDataRepository;

    private EncounterModalityRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new EncounterModalityRepositoryAdapter(
                springDataRepository,
                new EncounterModalityPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        EncounterModality encounterModality = EncounterModality.register(
                "Telemedicine",
                "TELEMEDICINE"
        );
        when(springDataRepository.findById(encounterModality.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(EncounterModalityJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EncounterModality saved = adapter.save(encounterModality);

        assertThat(saved.id()).isEqualTo(encounterModality.id());
        assertThat(saved.name()).isEqualTo("Telemedicine");
        assertThat(saved.code()).isEqualTo("TELEMEDICINE");
        assertThat(saved.active()).isTrue();
        verify(springDataRepository).save(any(EncounterModalityJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        EncounterModality encounterModality = EncounterModality.register(
                "Telemedicine",
                "TELEMEDICINE"
        );

        adapter.delete(encounterModality);

        verify(springDataRepository)
                .deleteById(encounterModality.id().value());
    }
}
