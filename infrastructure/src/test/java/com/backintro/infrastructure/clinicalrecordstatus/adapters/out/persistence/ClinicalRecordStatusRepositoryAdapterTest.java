package com.backintro.infrastructure.clinicalrecordstatus.adapters.out.persistence;

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

import com.backintro.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import com.backintro.infrastructure.clinicalrecordstatus.adapters.out.persistence.entity.ClinicalRecordStatusJpaEntity;
import com.backintro.infrastructure.clinicalrecordstatus.adapters.out.persistence.mapper.ClinicalRecordStatusPersistenceMapper;
import com.backintro.infrastructure.clinicalrecordstatus.adapters.out.persistence.repository.ClinicalRecordStatusJpaRepository;

@ExtendWith(MockitoExtension.class)
class ClinicalRecordStatusRepositoryAdapterTest {

    @Mock
    private ClinicalRecordStatusJpaRepository springDataRepository;

    private ClinicalRecordStatusRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ClinicalRecordStatusRepositoryAdapter(
                springDataRepository,
                new ClinicalRecordStatusPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        ClinicalRecordStatus clinicalRecordStatus =
                ClinicalRecordStatus.register("Open", "OPEN");
        when(springDataRepository.findById(
                clinicalRecordStatus.id().value()
        )).thenReturn(Optional.empty());
        when(springDataRepository.save(
                any(ClinicalRecordStatusJpaEntity.class)
        )).thenAnswer(invocation -> invocation.getArgument(0));

        ClinicalRecordStatus saved = adapter.save(clinicalRecordStatus);

        assertThat(saved.id()).isEqualTo(clinicalRecordStatus.id());
        assertThat(saved.name()).isEqualTo("Open");
        assertThat(saved.code()).isEqualTo("OPEN");
        verify(springDataRepository)
                .save(any(ClinicalRecordStatusJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        ClinicalRecordStatus clinicalRecordStatus =
                ClinicalRecordStatus.register("Open", "OPEN");

        adapter.delete(clinicalRecordStatus);

        verify(springDataRepository)
                .deleteById(clinicalRecordStatus.id().value());
    }
}
