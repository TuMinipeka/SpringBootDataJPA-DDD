package com.backintro.infrastructure.encounter.adapters.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backintro.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.backintro.domain.encounter.model.aggregate.Encounter;
import com.backintro.domain.encountermodality.model.valueobject.EncounterModalityId;
import com.backintro.domain.encounterstatus.model.valueobject.EncounterStatusId;
import com.backintro.domain.encountertype.model.valueobject.EncounterTypeId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.infrastructure.encounter.adapters.out.persistence.entity.EncounterJpaEntity;
import com.backintro.infrastructure.encounter.adapters.out.persistence.mapper.EncounterPersistenceMapper;
import com.backintro.infrastructure.encounter.adapters.out.persistence.repository.EncounterJpaRepository;

@ExtendWith(MockitoExtension.class)
class EncounterRepositoryAdapterTest {

    @Mock
    private EncounterJpaRepository springDataRepository;

    private EncounterRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new EncounterRepositoryAdapter(
                springDataRepository,
                new EncounterPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        LocalDateTime startedAt = LocalDateTime.of(2026, 10, 3, 9, 0);
        Encounter encounter = newEncounter(startedAt);
        when(springDataRepository.findById(encounter.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(EncounterJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Encounter saved = adapter.save(encounter);

        assertThat(saved.id()).isEqualTo(encounter.id());
        assertThat(saved.clinicalRecordId())
                .isEqualTo(encounter.clinicalRecordId());
        assertThat(saved.professionalId())
                .isEqualTo(encounter.professionalId());
        assertThat(saved.startedAt()).isEqualTo(startedAt);
        assertThat(saved.reasonForVisit()).isEqualTo("Routine follow-up");
        assertThat(saved.currentCondition()).isEqualTo("Stable");
        assertThat(saved.statusId()).isEqualTo(encounter.statusId());
        verify(springDataRepository).save(any(EncounterJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        Encounter encounter = newEncounter(
                LocalDateTime.of(2026, 10, 3, 9, 0)
        );

        adapter.delete(encounter);

        verify(springDataRepository).deleteById(encounter.id().value());
    }

    private Encounter newEncounter(LocalDateTime startedAt) {
        return Encounter.register(
                new ClinicalRecordId(UUID.randomUUID()),
                new ProfessionalId(UUID.randomUUID()),
                new EncounterTypeId(UUID.randomUUID()),
                startedAt,
                "Routine follow-up",
                "Stable",
                new EncounterModalityId(UUID.randomUUID()),
                new EncounterStatusId(UUID.randomUUID())
        );
    }
}
